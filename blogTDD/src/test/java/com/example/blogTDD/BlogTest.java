package com.example.blogTDD;

import com.example.blogTDD.models.Blog;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlogTest {

    @Test
    public void testCreateBlog() {
        Blog blog = new Blog("Test Title", "Test Content");


        assertNotNull(blog);
        assertEquals("Test Title", blog.getTitle());
        assertEquals("Test Content", blog.getContent());
    }


}
