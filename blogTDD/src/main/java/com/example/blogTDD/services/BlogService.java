package com.example.blogTDD.services;

import com.example.blogTDD.models.Blog;
import com.example.blogTDD.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {


    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Optional<Blog> updateBlog(Long id, Blog updatedBlog) {
        Optional<Blog> existingBlog = blogRepository.findById(id);

        if (existingBlog.isPresent()) {
            Blog blogToUpdate = existingBlog.get();
            blogToUpdate.setTitle(updatedBlog.getTitle());
            blogToUpdate.setContent(updatedBlog.getContent());
            return Optional.of(blogRepository.save(blogToUpdate));
        }

        return Optional.empty();
    }

    



}
