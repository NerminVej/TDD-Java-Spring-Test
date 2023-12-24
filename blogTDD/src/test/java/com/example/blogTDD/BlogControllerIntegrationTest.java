package com.example.blogTDD;

import com.example.blogTDD.controllers.BlogController;
import com.example.blogTDD.models.Blog;
import com.example.blogTDD.services.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BlogController.class)
class BlogControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogService blogService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllBlogs() throws Exception {
        Blog blog1 = new Blog(1L, "Title 1", "Content 1");
        Blog blog2 = new Blog(2L, "Title 2", "Content 2");
        List<Blog> blogs = Arrays.asList(blog1, blog2);

        when(blogService.getAllBlogs()).thenReturn(blogs);

        mockMvc.perform(get("/api/blogs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Title 1")))
                .andExpect(jsonPath("$[1].title", is("Title 2")));
    }


    @Test
    void testGetBlogById() throws Exception {
        Long blogId = 1L;
        Blog blog = new Blog(blogId, "Test Title", "Test Content");

        when(blogService.getBlogById(blogId)).thenReturn(Optional.of(blog));

        mockMvc.perform(get("/api/blogs/{id}", blogId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.content", is("Test Content")));
    }

    @Test
    void testCreateBlog() throws Exception {
        Blog blogToCreate = new Blog("Test Title", "Test Content");
        Blog createdBlog = new Blog(1L, "Test Title", "Test Content");

        when(blogService.createBlog(any())).thenReturn(createdBlog);
        
        mockMvc.perform(post("/api/blogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(blogToCreate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.content", is("Test Content")));
    }

}
