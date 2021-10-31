package armeria.rest.tutorial

import com.linecorp.armeria.common.HttpResponse
import com.linecorp.armeria.common.HttpStatus
import com.linecorp.armeria.server.annotation.Blocking
import com.linecorp.armeria.server.annotation.Default
import com.linecorp.armeria.server.annotation.Delete
import com.linecorp.armeria.server.annotation.ExceptionHandler
import com.linecorp.armeria.server.annotation.Get
import com.linecorp.armeria.server.annotation.Param
import com.linecorp.armeria.server.annotation.Post
import com.linecorp.armeria.server.annotation.ProducesJson
import com.linecorp.armeria.server.annotation.Put
import com.linecorp.armeria.server.annotation.RequestConverter
import com.linecorp.armeria.server.annotation.RequestObject
import java.util.concurrent.ConcurrentHashMap
import java.util.stream.Collectors

class BlogService {
    private val blogPosts: MutableMap<Int, BlogPost> = ConcurrentHashMap()

    @Post("/blogs")
    @RequestConverter(BlogPostRequestConverter::class)
    fun createBlogPost(blogPost: BlogPost): HttpResponse {
        blogPosts[blogPost.id] = blogPost
        return HttpResponse.ofJson(blogPost)
    }

    @Get("/blogs/:id")
    fun getBlogPost(@Param("id") id: Int): HttpResponse {
        val blogPost = blogPosts[id]
        return HttpResponse.ofJson(blogPost)
    }

    @Get("/blogs")
    @ProducesJson
    fun getBlogPosts(@Param("descending") @Default("true") descending: Boolean): Iterable<BlogPost> {
        if (descending) {
            return blogPosts
                .toList()
                .sortedByDescending { it.second.id }
                .toMap()
                .values
                .stream()
                .collect(Collectors.toList())
        }
        return blogPosts
            .values
            .stream()
            .collect(Collectors.toList())
    }

    @Put("/blogs/:id")
    @RequestConverter(BlogPostRequestConverter::class)
    fun updateBlogPost(@Param("id") id: Int, @RequestObject blogPost: BlogPost): HttpResponse {
        blogPosts[id]?.let {
            val newBlogPost = BlogPost(id, blogPost.title, blogPost.content, it.createdAt, blogPost.createdAt)
            blogPosts[id] = newBlogPost
            return HttpResponse.ofJson(newBlogPost)
        } ?: return HttpResponse.of(HttpStatus.NOT_FOUND)
    }

    @Blocking
    @Delete("/blogs/:id")
    @ExceptionHandler(BadRequestExceptionHandler::class)
    fun deleteBlogPost(@Param("id") id: Int): HttpResponse {
        blogPosts.remove(id) ?: throw IllegalArgumentException("The blog post does not exist. id: $id")
        return HttpResponse.of(HttpStatus.NO_CONTENT)
    }
}
