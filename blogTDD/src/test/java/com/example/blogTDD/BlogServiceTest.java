package com.example.blogTDD;

import com.example.blogTDD.models.Blog;
import com.example.blogTDD.repositories.BlogRepository;
import com.example.blogTDD.services.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;


public class BlogServiceTest {


    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogService blogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        when(blogRepository.findAll()).thenReturn(blogs);

        List<Blog> result = blogService.getAllBlogs();

        assertEquals(blogs, result);
        verify(blogRepository, times(1)).findAll();
    }

    @Test
    void getBlogById() {
        Long blogId = 1L;
        Blog blog = new Blog("Test Title", "Test Content");
        when(blogRepository.findById(blogId)).thenReturn(Optional.of(blog));

        Optional<Blog> result = blogService.getBlogById(blogId);

        assertTrue(result.isPresent());
        assertEquals(blog, result.get());
        verify(blogRepository, times(1)).findById(blogId);
    }


}
