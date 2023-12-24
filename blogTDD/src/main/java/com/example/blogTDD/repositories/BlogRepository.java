package com.example.blogTDD.repositories;

import com.example.blogTDD.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlogRepository extends JpaRepository<Blog, Long> {
}
