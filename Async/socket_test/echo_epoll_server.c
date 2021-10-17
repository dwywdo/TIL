#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
// 리눅스에서만 사용 가능
#include <sys/epoll.h>

#define BUF_SIZE 100
#define EPOLL_SIZE 50
void error_handling(char *buf);

int main(int argc, char *argv[]) {
    int serv_sock, clnt_sock;
    struct sockaddr_in serv_adr, clnt_adr;
    socklen_t adr_sz;
    int str_len, i;
    char buf[BUF_SIZE];

    struct epoll_event *ep_events;
    struct epoll_event event;
    int epfd, event_cnt;

    if (argc != 2) {
            printf("Usage : %s <port>\n", argv[0]);
            exit(1);
        }

    serv_sock = socket(PF_INET, SOCK_STREAM, 0);
    memset(&serv_adr, 0, sizeof(serv_adr));
    serv_adr.sin_family = AF_INET;
    serv_adr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_adr.sin_port = htons(atoi(argv[1]));
    if (bind(serv_sock, (struct sockaddr*) &serv_adr, sizeof(serv_adr)) == -1)
        error_handling("bind() error");
    if (listen(serv_sock, 5) == -1)
        error_handling("listen() error");

    // 커널이 관리하는 epoll 인스턴스라 불리는 파일 디스크립터의 저장소 생성
    // 성공 시 epoll 파일 디스크립터, 실패시 -1 반환
    epfd = epoll_create(EPOLL_SIZE);
    ep_events = malloc(sizeof(struct epoll_event)*EPOLL_SIZE);

    event.events = EPOLLIN;
    event.data.fd = serv_sock;
    // 파일 디스크립터(serv_sock)를 epoll 인스턴스에 등록한다. (관찰대상의 관찰 이벤트 유형은 EPOLLIN)
    epoll_ctl(epfd, EPOLL_CTL_ADD, serv_sock, &event);

    while(1) {
            // 성공 시 이벤트가 발생한 파일 디스크립터이ㅡ 수, 실패 시 -1 반환
            // 두 번째 인자로 전달된 주소의 메모리 공간에 이벤트 발생한 파일 디스크립터에 대한 정보가 들어있다.
            event_cnt = epoll_wait(epfd, ep_events, EPOLL_SIZE, -1);
            if (event_cnt == -1) {
                        puts("epoll_wait() error");
                        break;
                    }
    
            for (i=0; i<event_cnt; i++) {
                        if (ep_events[i].data.fd == serv_sock) {
                                        adr_sz = sizeof(clnt_adr);
                                        clnt_sock= accept(serv_sock, (struct sockaddr*)&clnt_adr, &adr_sz);
                                        event.events = EPOLLIN;
                                        event.data.fd = clnt_sock;
                                        // 파일 디스크립터(clnt_sock)를 epoll 인스턴스에 등록한다. (관찰대상의 관찰 이벤트 유형은 EPOLLIN)
                                        epoll_ctl(epfd, EPOLL_CTL_ADD, clnt_sock, &event);
                                        printf("connected client: %d \n", clnt_sock);
                                    } else {
                                                    str_len = read(ep_events[i].data.fd, buf, BUF_SIZE);
                                                    if (str_len == 0) { // close request!
                                                                        epoll_ctl(epfd, EPOLL_CTL_DEL, ep_events[i].data.fd, NULL);
                                                                        close(ep_events[i].data.fd);
                                                                        printf("closed client: %d \n", ep_events[i].data.fd);
                                                                    } else {
                                                                                        write(ep_events[i].data.fd, buf, str_len);    // echo!
                                                                                    }
                                                }
                    }
        }
    
    close(serv_sock);
    close(epfd);
    return 0;
}

void error_handling(char *buf) {
    fputs(buf, stderr);
    fputc('\n', stderr);
    exit(1);
}
