package me.dwywdo.io.basic.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        // Server Socket 생성
        serverSocket = new ServerSocket(port);

        // 생성된 Server Socket을 기반으로 클라이언트로부터의 연결 요청을 기다린다.
        // accept하는 순간, 요청이 들어올 때까지 서버는 Block된다.
        clientSocket = serverSocket.accept();

        // 요청이 들어오는 순간 Socket 객체가 clientSocket으로서 생성되어 반환된다.
        // 이후 이 객체의 OutputStream / InputStream을 통해 데이터를 송수신할 수 있게 된다.
        // 소켓이 닫히기 전까지는 이 소켓을 이용해 계속 통신할 수 있다.
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String greeting = in.readLine();
        if ("hello server".equals(greeting)) {
            out.println("hello client");
        }
        else {
            out.println("unrecognized greeting");
        }
        // 한번 응답한 후 서버가 종료되기 때문에 계속 요청을 받을 수는 없는 상태이다.
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        GreetServer server = new GreetServer();
        server.start(6666);
    }
}
