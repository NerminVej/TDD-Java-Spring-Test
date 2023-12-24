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

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void testCreateBlog() {
        Blog blogToCreate = new Blog("Test Title", "Test Content");
        Blog savedBlog = new Blog(1L, "Test Title", "Test Content");

        when(blogRepository.save(blogToCreate)).thenReturn(savedBlog);

        Blog createdBlog = blogService.createBlog(blogToCreate);

        assertNotNull(createdBlog.getId());
        assertEquals("Test Title", createdBlog.getTitle());
        assertEquals("Test Content", createdBlog.getContent());

        verify(blogRepository, times(1)).save(blogToCreate);
    }

    @Test
    void testUpdateBlog() {
        Long blogId = 1L;
        Blog existingBlog = new Blog(blogId, "Existing Title", "Existing Content");
        Blog updatedBlog = new Blog(blogId, "Updated Title", "Updated Content");

        when(blogRepository.findById(blogId)).thenReturn(Optional.of(existingBlog));
        when(blogRepository.save(existingBlog)).thenReturn(updatedBlog);

        Optional<Blog> result = blogService.updateBlog(blogId, updatedBlog);

        assertTrue(result.isPresent());
        assertEquals("Updated Title", result.get().getTitle());
        assertEquals("Updated Content", result.get().getContent());

        verify(blogRepository, times(1)).findById(blogId);
        verify(blogRepository, times(1)).save(existingBlog);
    }

    @Test
    void testUpdateBlogWhenNotFound() {
        Long blogId = 1L;
        Blog updatedBlog = new Blog(blogId, "Updated Title", "Updated Content");

        when(blogRepository.findById(blogId)).thenReturn(Optional.empty());

        Optional<Blog> result = blogService.updateBlog(blogId, updatedBlog);

        assertFalse(result.isPresent());

        verify(blogRepository, times(1)).findById(blogId);
        verify(blogRepository, never()).save(any());
    }

    @Test
    void testDeleteBlog() {
        Long blogId = 1L;
        Blog blogToDelete = new Blog(blogId, "Test Title", "Test Content");

        when(blogRepository.findById(blogId)).thenReturn(Optional.of(blogToDelete));

        boolean result = blogService.deleteBlog(blogId);

        assertTrue(result);

        verify(blogRepository, times(1)).findById(blogId);
        verify(blogRepository, times(1)).delete(blogToDelete);
    }

    @Test
    void testDeleteBlogWhenNotFound() {
        Long blogId = 1L;

        when(blogRepository.findById(blogId)).thenReturn(Optional.empty());

        boolean result = blogService.deleteBlog(blogId);

        assertFalse(result);

        verify(blogRepository, times(1)).findById(blogId);
        verify(blogRepository, never()).delete(any());
    }


}
