package com.example.blogTDD;

import com.example.blogTDD.controllers.BlogController;
import com.example.blogTDD.models.Blog;
import com.example.blogTDD.services.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BlogControllerTest {

    @InjectMocks
    private BlogController blogController;

    @Mock
    private BlogService blogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBlogs() {
        List<Blog> blogs = Arrays.asList(new Blog(1L, "Title1", "Content1"), new Blog(2L, "Title2", "Content2"));

        when(blogService.getAllBlogs()).thenReturn(blogs);

        ResponseEntity<List<Blog>> response = blogController.getAllBlogs();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blogs, response.getBody());

        verify(blogService, times(1)).getAllBlogs();
    }

    @Test
    void testGetBlogById() {
        Long blogId = 1L;
        Blog blog = new Blog(blogId, "Test Title", "Test Content");

        when(blogService.getBlogById(blogId)).thenReturn(Optional.of(blog));

        ResponseEntity<Blog> response = blogController.getBlogById(blogId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blog, response.getBody());

        verify(blogService, times(1)).getBlogById(blogId);
    }

    @Test
    void testGetBlogByIdNotFound() {
        Long blogId = 1L;

        when(blogService.getBlogById(blogId)).thenReturn(Optional.empty());

        ResponseEntity<Blog> response = blogController.getBlogById(blogId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(blogService, times(1)).getBlogById(blogId);
    }





}
