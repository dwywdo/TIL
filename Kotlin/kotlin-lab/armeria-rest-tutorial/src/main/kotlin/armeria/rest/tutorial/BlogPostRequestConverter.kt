package armeria.rest.tutorial

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.linecorp.armeria.common.AggregatedHttpRequest
import com.linecorp.armeria.server.ServiceRequestContext
import com.linecorp.armeria.server.annotation.RequestConverterFunction
import java.lang.reflect.ParameterizedType
import java.util.concurrent.atomic.AtomicInteger

class BlogPostRequestConverter: RequestConverterFunction {
    companion object {
        private val objectMapper: ObjectMapper = ObjectMapper()
        private val idGenerator: AtomicInteger = AtomicInteger(0)
        fun stringValue(jsonNode: JsonNode, field: String): String {
            val value: JsonNode = jsonNode.get(field) ?: throw IllegalArgumentException("$field is missing")
            return value.textValue()
        }
    }

    override fun convertRequest(
        ctx: ServiceRequestContext,
        request: AggregatedHttpRequest,
        expectedResultType: Class<out Any>,
        expectedParameterizedResultType: ParameterizedType?
    ): Any? {
        if (expectedResultType == BlogPost::class.java) {
            val jsonNode = objectMapper.readTree(request.contentUtf8())
            val id = idGenerator.getAndIncrement()
            val title = stringValue(jsonNode, "title")
            val content = stringValue(jsonNode, "content")
            return BlogPost(id, title, content)
        }
        return RequestConverterFunction.fallthrough()
    }
}
