package com.example.TDDTest.post;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
public class PostControllerTest {


    @Autowired
    MockMvc mockMvc;



    List<Post> posts = new ArrayList<>();

    @BeforeEach
    void setUp() {
        posts = List.of(
                new Post(1,1,"Hello, World!", "This is my first post.",null),
                new Post(2,1,"Second Post", "This is my second post.",null)
        );
    }


}
