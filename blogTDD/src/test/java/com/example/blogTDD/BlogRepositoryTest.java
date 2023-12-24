package com.example.blogTDD;

import com.example.blogTDD.models.Blog;
import com.example.blogTDD.repositories.BlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJdbcTest
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @Test
    public void testSaveBlog() {

        Blog blogToSave = new Blog("Test Title", "Test Content");


        Blog savedBlog = blogRepository.save(blogToSave);


        assertNotNull(savedBlog.getId());
        assertEquals("Test Title", savedBlog.getTitle());
        assertEquals("Test Content", savedBlog.getContent());
    }

}
