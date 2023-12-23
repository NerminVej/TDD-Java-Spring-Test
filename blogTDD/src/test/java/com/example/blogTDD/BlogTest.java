package com.example.blogTDD;

import org.junit.jupiter.api.Test;

public class BlogTest {

    @Test
    public void testCreateBlog() {
        Blog blog = new Blog("Test Title", "Test Content");


        assertNotNull(blog);
        assertEquals("Test Title", blog.getTitle());
        assertEquals("Test Content", blog.getContent());
    }


}
