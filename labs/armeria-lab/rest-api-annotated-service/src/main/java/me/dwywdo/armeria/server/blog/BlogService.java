package me.dwywdo.armeria.server.blog;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.annotation.Post;
import com.linecorp.armeria.server.annotation.RequestConverter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class BlogService {
    private final Map<Integer, BlogPost> blogPostMap = new ConcurrentHashMap<>();

    @Post("/blogs")
    @RequestConverter(BlogPostRequestConverter.class)
    public HttpResponse createPostBlog(BlogPost blogPost) {
        return HttpResponse.ofJson(blogPost);
    }
}
