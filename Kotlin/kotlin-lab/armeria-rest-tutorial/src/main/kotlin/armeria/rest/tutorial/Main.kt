package armeria.rest.tutorial

import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.ServerBuilder
import com.linecorp.armeria.server.docs.DocService
import mu.KotlinLogging

fun main() {
    val logger = KotlinLogging.logger {}
    val server = newServer(8080)
    Runtime.getRuntime().addShutdownHook(
        Thread {
            server.stop().join()
            logger.info { "Server has been stopped" }
        }
    )
    server.start().join()
    logger.info { "Server has been started. Serving DocService at http:127.0.0.1:${server.activeLocalPort()}/docs"}
}

private fun newServer(port: Int): Server {
    val serverBuilder: ServerBuilder = Server.builder()
    val docService: DocService = DocService.builder()
        .exampleRequests(BlogService::class.java,
            "createBlogPost",
            "{\"title\":\"My first blog\", \"content\":\"Hello Armeria!\"}")
        .build()
    return serverBuilder
        .http(port)
        // .service("/") {
        //         _, _ -> HttpResponse.of("Hello, Armeria!")
        // }
        .annotatedService(BlogService())
        .serviceUnder("/docs", docService)
        .build()
}

