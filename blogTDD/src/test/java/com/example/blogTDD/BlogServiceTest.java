package com.example.blogTDD;

import com.example.blogTDD.models.Blog;
import com.example.blogTDD.repositories.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

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
        // Arrange
        List<Blog> blogs = new ArrayList<>();
        when(blogRepository.findAll()).thenReturn(blogs);

        // Act
        List<Blog> result = blogService.getAllBlogs();

        // Assert
        assertEquals(blogs, result);
        verify(blogRepository, times(1)).findAll();
    }


}
