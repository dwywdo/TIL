import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * GET 요청 처리기
 */
public class Server_1 {
    public static void main(String[] args) throws IOException {
        Server_1 server = new Server_1();
        server.boot();
    }

    private void boot() throws IOException {
        serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        int oneInt = -1;
        byte oldByte = (byte)-1;
        StringBuilder sb = new StringBuilder();
        int lineNumber = 0;
        while(-1 != (oneInt = in.read())) {
            byte thisByte = (byte)oneInt;
            if (thisByte == Server_1.LF && oldByte == Server_1.CR) {
                // CRLF가 완성됨. 직전 CRLF로부터 여기까지가 한 행
                // -2가 아닌 -1인 이유? 아직 LF가 버퍼에 들어가기 전이기 때문에
                String oneLine = sb.substring(0, sb.length() - 1);
                lineNumber++;
                System.out.printf("%d: %s\n", lineNumber, oneLine);
                if (oneLine.length() <= 0) {
                    // 내용이 없는 행
                    // 즉 메시지 헤더의 마지막인 경우
                    System.out.println("[SYS] 내용이 없는 헤더, 즉 메시지의 끝");
                    // 메시지 바디는 처리하지 않는다 (GET만 들어온다고 가정)
                    break;
                }
                sb.setLength(0);
            } else {
                sb.append((char)thisByte);
            }
            oldByte = (byte)oneInt;
        }
        out.close();
        in.close();
        socket.close();
    }

    public static final byte CR = '\r';
    public static final byte LF = '\n';

    private ServerSocket serverSocket;
}
