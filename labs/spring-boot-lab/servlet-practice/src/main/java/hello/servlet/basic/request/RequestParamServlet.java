package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameterNames().asIterator().forEachRemaining(
                paramName -> System.out.println(paramName + " = " + request.getParameter(paramName))
        );

        final String username = request.getParameter("username");
        final String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);

        final String[] usernames = request.getParameterValues("username");
        System.out.println("usernames = " + Arrays.toString(usernames));

        final Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("parameterMap = " + parameterMap);

        response.getWriter().write("ok");
    }
}
