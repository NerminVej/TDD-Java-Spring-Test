package com.example.blogTDD;

import com.example.blogTDD.controllers.BlogController;
import com.example.blogTDD.models.Blog;
import com.example.blogTDD.services.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        mockMvc.perform(get("/blogs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Title 1")))
                .andExpect(jsonPath("$[1].title", is("Title 2")));
    }

}
