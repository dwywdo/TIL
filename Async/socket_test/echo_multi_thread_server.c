#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <pthread.h>

#define BUF_SIZE 30

void * handle_clnt(void * arg);
void error_handling(char * msg);

int main(int argc, char *argv[]) {
    int serv_sock, clnt_sock;
    struct sockaddr_in serv_addr, clnt_addr;

    pthread_t t_id;
    socklen_t addr_sz;
    int str_len, state;
    char buf[BUF_SIZE];
    if (argc != 2) {
        printf("Usage: %s <port>\n", argv[0]);
        exit(1);
    }

    // 1. Create one socket
    serv_sock = socket(PF_INET, SOCK_STREAM, 0);
    memset(&serv_addr, 0, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(atoi(argv[1]));

    // 2. Assign IP and Port to the created socket
    if (bind(serv_sock, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) == -1) {
        error_handling("bind() error");
    }

    // 3. Register the created socket as listening socket
    if (listen(serv_sock, 5) == -1) {
        error_handling("listen() error");
    }

    while(1) {
        addr_sz = sizeof(clnt_addr);
        // 4. Main thread calls accpet() w/ listening socket to receive connection request
        clnt_sock = accept(serv_sock, (struct sockaddr*)&clnt_addr, &addr_sz);

        if (clnt_sock == -1)
            continue;

        puts("new client connected...");

        // 5. Client-Connected socket's fild descriptior is passwed to worker thread
        pthread_create(&t_id, NULL, handle_clnt, (void*)&clnt_sock);
        pthread_detach(t_id);
    }
    close(serv_sock);
    return 0;
}

void * handle_clnt(void * arg) {
    int clnt_sock = *((int*)arg);
    int str_len = 0, i;
    char buf[BUF_SIZE];

    while((str_len = read(clnt_sock, buf, BUF_SIZE)) != 0) {
        write(clnt_sock, buf, str_len);
    }

    close(clnt_sock);
    return NULL;
}

void error_handling(char * msg) {
    fputs(msg, stderr);
    fputc('\n', stderr);
    exit(1);
}
