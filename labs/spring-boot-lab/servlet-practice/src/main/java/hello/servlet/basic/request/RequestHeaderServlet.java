package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    private static void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- REQUEST HEADER UTILS START ---");

        System.out.println("--- REQUEST HEADER - HOST ---");
        System.out.println("request.getServerName() = " + request.getServerName());
        System.out.println("request.getServerPort() = " + request.getServerPort());

        System.out.println("--- REQUEST HEADER - Accept-Language ---");
        request.getLocales().asIterator()
                        .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();

        System.out.println("--- REQUEST HEADER - Cookie ---");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }

        System.out.println("--- REQUEST HEADER - Content ---");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("--- REQUEST HEADER UTILS END ---");
        System.out.println();
    }

    private static void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST LINE START ---");
        System.out.println("request.getMethod() = " + request.getMethod());
        System.out.println("request.getProtocol() = " + request.getProtocol());
        System.out.println("request.getScheme() = " + request.getScheme());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure());
        System.out.println("--- REQUEST LINE END ---");
        System.out.println();
    }

    private static void printHeaders(HttpServletRequest request) {
        System.out.println("--- REQUEST HEADER START ---");
        /** Old Way
         * final Enumeration<String> headerNames = request.getHeaderNames();
         * while (headerNames.hasMoreElements()) {
         *     final String headerName = headerNames.nextElement();
         *     System.out.println(headerName + ": " + request.getHeader(headerName));
         * }
         */
        request.getHeaderNames().asIterator()
                        .forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));

        System.out.println("--- REQUEST HEADER END ---");
        System.out.println();
    }

    private static void printEtc(HttpServletRequest request) {
        System.out.println("--- REQUEST HEADER ETC START ---");

        System.out.println("--- REQUEST HEADER ETC - REMOTE ---");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());

        System.out.println("--- REQUEST HEADER ETC - Local ---");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());

        System.out.println("--- REQUEST HEADER ETC END ---");
    }
}
