package armeria.rest.tutorial

import com.fasterxml.jackson.databind.ObjectMapper
import com.linecorp.armeria.common.HttpRequest
import com.linecorp.armeria.common.HttpResponse
import com.linecorp.armeria.common.HttpStatus
import com.linecorp.armeria.server.ServiceRequestContext
import com.linecorp.armeria.server.annotation.ExceptionHandlerFunction

class BadRequestExceptionHandler : ExceptionHandlerFunction {
    override fun handleException(ctx: ServiceRequestContext, req: HttpRequest, cause: Throwable): HttpResponse {
        if (cause is IllegalArgumentException) {
            val message = cause.message
            val objectNode = objectMapper.createObjectNode()
            objectNode.put("error", message)
            return HttpResponse.ofJson(HttpStatus.BAD_REQUEST, objectNode)
        }
        return ExceptionHandlerFunction.fallthrough()
    }
    companion object {
        val objectMapper = ObjectMapper()
    }
}
