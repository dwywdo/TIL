
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Copy {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length < 1) {
            System.out.println("파일 이름 지정과 버퍼 사용 여부가 필요합니다.");
            System.exit(0);
        }
        Copy c = new Copy(args[0]);
        long before = System.currentTimeMillis();

        if (args.length > 1 && "no".equals(args[1])) {
            c.noBufferAction();
        } else {
            c.bufferAction();
        }
        long after = System.currentTimeMillis();
        System.out.printf("%.3f\n", (float) ((after - before) / 1000f));
    }

    private Copy(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;
    private String targetFileName;

    private void noBufferAction() throws IOException {
        targetFileName = fileName.concat("-nob");
        InputStream in = new FileInputStream(fileName);
        OutputStream out = new FileOutputStream(targetFileName);
        int oneInt = -1;
        while (-1 != (oneInt = in.read())) {
            out.write(oneInt);
        }
        in.close();
        out.close();
    }

    private void bufferAction() throws IOException {
        targetFileName = fileName.concat("-useb");
        InputStream in = new FileInputStream(fileName);
        OutputStream out = new FileOutputStream(targetFileName);
        byte[] buffer = new byte[1024];
        int readSize = 0;
        while (0 < (readSize = in.read(buffer))) {
            out.write(buffer, 0, readSize);
        }
        in.close();
        out.close();
    }
}
