import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 메시지 바디 처리 - Content-Length 인식
 */
public class Server_2 {
    public static void main(String[] args) throws IOException {
        Server_2 server = new Server_2();
        server.boot();
    }

    private void boot() throws IOException {
        serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        int oneInt = -1;
        byte oldByte = (byte)-1;
        StringBuilder sb = new StringBuilder();
        int lineNumber = 0;
        boolean bodyFlag = false;
        String method = null;
        String requestUrl = null;
        String httpVersion = null;
        int contentLength = -1;
        int bodyRead = 0;
        List<Byte> bodyByteList = null;
        Map<String, String> headerMap = new HashMap<String, String>();
        while (-1 != (oneInt = in.read())) {
            byte thisByte = (byte)oneInt;
            if (bodyFlag) {
                bodyRead++;
                bodyByteList.add(thisByte);
                if (bodyRead >= contentLength) {
                    break;
                }
            } else {
                if (thisByte == Server_2.LF && oldByte == Server_2.CR) {
                    String oneLine = sb.substring(0, sb.length() - 1);
                    lineNumber++;
                    if (lineNumber == 1) {
                        // 요청의 첫 행, HTTP Method, 요청 URL, 버전을 알아낸다.
                        int firstBlank = oneLine.indexOf(" ");
                        int secondBlank = oneLine.lastIndexOf(" ");
                        method = oneLine.substring(0, firstBlank);
                        requestUrl = oneLine.substring(firstBlank + 1, secondBlank);
                        httpVersion = oneLine.substring(secondBlank + 1);
                    } else {
                        if (oneLine.length() <= 0) {
                            bodyFlag = true;
                            if ("GET".equals(method)) {
                               // GET 방식이면 바디가 없다
                               break;
                            }
                            String contentLengthValue = headerMap.get("Content-Length");
                            if (contentLengthValue != null) {
                                contentLength = Integer.parseInt(contentLengthValue.trim());
                                bodyFlag = true;
                                bodyByteList = new ArrayList<Byte>();
                            }
                            continue;
                        }
                        int indexOfColon = oneLine.indexOf(":");
                        String headerName = oneLine.substring(0, indexOfColon);
                        String headerValue = oneLine.substring(indexOfColon + 1);
                        headerMap.put(headerName, headerValue);
                    }
                    sb.setLength(0);
                } else {
                    sb.append((char)thisByte);
                }
            }
            oldByte = (byte)oneInt;
        }
        in.close();
        socket.close();

        System.out.printf("METHOD: %s REQ: %s HTTP VER: %s\n", method, requestUrl, httpVersion);
        System.out.println("Header list");
        Set<String> keySet = headerMap.keySet();
        Iterator<String> keyIter = keySet.iterator();

        while (keyIter.hasNext()) {
            String headerName = keyIter.next();
            System.out.printf("  Key: %s Value: %s\n", headerName, headerMap.get(headerName));
        }

        if(bodyByteList != null) {
            System.out.print("Message Body-->");
            for (byte oneByte : bodyByteList) {
                System.out.print(oneByte);
            }
            System.out.println("<--");
        }
        System.out.println("End of HTTP Message");
    }

    public static final byte CR = '\r';
    public static final byte LF = '\n';

    private ServerSocket serverSocket;
}
