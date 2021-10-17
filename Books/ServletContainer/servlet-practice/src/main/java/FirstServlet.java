import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
    @Override
    public void init() {
        System.out.printf("%s이 초기화되었습니다.\n", getServletName());
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("Hello World!\n");
    }

    @Override
    public void destroy() {
        System.out.println("이젠 안녕히..");
    }

    @Override
    public String getServletName() {
        return "안녕 서블릿";
    }
}
