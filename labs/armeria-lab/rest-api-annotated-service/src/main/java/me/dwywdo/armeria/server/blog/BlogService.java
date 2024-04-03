package me.dwywdo.armeria.server.blog;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.server.annotation.Default;
import com.linecorp.armeria.server.annotation.Delete;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;
import com.linecorp.armeria.server.annotation.Post;
import com.linecorp.armeria.server.annotation.ProducesJson;
import com.linecorp.armeria.server.annotation.Put;
import com.linecorp.armeria.server.annotation.RequestConverter;
import com.linecorp.armeria.server.annotation.RequestObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public final class BlogService {
    private final Map<Integer, BlogPost> blogPosts = new ConcurrentHashMap<>();

    @Post("/blogs")
    @RequestConverter(BlogPostRequestConverter.class)
    public HttpResponse createPostBlog(BlogPost blogPost) {
        blogPosts.put(blogPost.getId(), blogPost);
        return HttpResponse.ofJson(blogPost);
    }

    /**
     * Retrieves a {@link BlogPost} whose {@link BlogPost#getId()} is the {@code :id} in the path parameter.
     */
    @Get("/blogs/:id")
    public HttpResponse getBlogPost(@Param("id") int id) {
        final BlogPost blogPost = blogPosts.get(id);
        return HttpResponse.ofJson(blogPost);
    }

    /**
     * Retrieves all {@link BlogPost}s created so far in the reverse chronological order .
     */
    @Get("/blogs")
    @ProducesJson
    public Iterable<BlogPost> getBlogPosts(@Param("descending") @Default("true") boolean descending) {
        // Descending
        if (descending) {
            return blogPosts.entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Comparator.comparingInt(Entry::getKey)))
                    .map(Entry::getValue).collect(Collectors.toList());
        }

        // Ascending
        return blogPosts.values().stream().collect(Collectors.toList());
    }

    @Put("/blogs/:id")
    public HttpResponse updateBlogPost(@Param("id") int id, @RequestObject BlogPost blogPost) {
        final BlogPost oldBlogPost = blogPosts.get(id);
        if (oldBlogPost == null) { return HttpResponse.of(HttpStatus.NOT_FOUND); }

        final BlogPost newBlogPost = new BlogPost(id, blogPost.getTitle(), blogPost.getContent(), oldBlogPost.getCreatedAt(), blogPost.getCreatedAt());
        blogPosts.put(id, newBlogPost);

        return HttpResponse.ofJson(newBlogPost);
    }
}
