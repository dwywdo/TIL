package me.dwywdo.armeria.server.blog;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.docs.DocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    static Server newServer(int port) {
        final ServerBuilder sb = Server.builder();
        final DocService docService =
                DocService.builder()
                        .exampleRequests(
                                BlogService.class,
                                "createBlogPost",
                                "{\"title\":\"My first blog\", \"content\":\"Hello Armeria!\"}")
                        .build();

        return sb.http(port)
                .annotatedService(new BlogService())
                .serviceUnder("/docs", docService)
                .build();
    }

    public static void main(String[] args) {
        final Server server = newServer(8080);
        server.closeOnJvmShutdown();
        server.start().join();
        logger.info("Server has been started. Serving DocService at http://127.0.0.1:{}/docs", server.activeLocalPort());
    }
}
