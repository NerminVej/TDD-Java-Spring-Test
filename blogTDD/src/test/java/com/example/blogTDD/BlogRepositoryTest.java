package com.example.blogTDD;

import com.example.blogTDD.models.Blog;
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
        // Arrange
        Blog blogToSave = new Blog("Test Title", "Test Content");

        // Act
        Blog savedBlog = blogRepository.save(blogToSave);

        // Assert
        assertNotNull(savedBlog.getId());
        assertEquals("Test Title", savedBlog.getTitle());
        assertEquals("Test Content", savedBlog.getContent());
    }

}
