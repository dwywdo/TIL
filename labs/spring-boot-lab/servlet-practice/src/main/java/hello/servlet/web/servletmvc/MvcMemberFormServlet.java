package hello.servlet.web.servletmvc;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String viewPath = "/WEB-INF/views/new-form.jsp";
        // Controller -> View 이동 시 사용하는 RequestDispatcher
        // 서버 내부에서 호출이 다시 발생 (클라이언트에 응답이 갔다가 클라이언트가 다시 요청을 하는 것이 아님
        // 즉 Redirect와는 다른 방식으로 동작한다.
        // 클라이언트 -> 서버 호출 [1] -> 서버 내부에서 Forward 호출 -> 응답이 호출 [1]의 응답으로 내려가게 됨
        final RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
