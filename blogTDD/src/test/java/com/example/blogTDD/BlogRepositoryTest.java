package com.example.blogTDD;

import com.example.blogTDD.models.Blog;
import com.example.blogTDD.repositories.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
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

    @Test
    public void testFindBlogById() {
        Blog blogToSave = new Blog("Test Title", "Test Content");
        Blog savedBlog = blogRepository.save(blogToSave);

        Blog foundBlog = blogRepository.findById(savedBlog.getId()).orElse(null);

        assertNotNull(foundBlog);
        assertEquals("Test Title", foundBlog.getTitle());
        assertEquals("Test Content", foundBlog.getContent());
    }

}
