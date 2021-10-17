import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 메시지의 끝은 어디인가?
 */
public class Server_0 {
    public static void main(String[] args) throws IOException {
        Server_0 server = new Server_0();
        server.boot();
    }

    private void boot() throws IOException {
        serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        int oneInt = -1;
        while(-1 != (oneInt = in.read())) {
            System.out.print((char)oneInt);
        }
        out.close();
        in.close();
        socket.close();
    }
    private ServerSocket serverSocket;
}
