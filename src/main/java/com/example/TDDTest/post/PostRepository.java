package com.example.TDDTest.post;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

interface PostRepository extends ListCrudRepository<Post,Integer> {

    Optional<Post> findByTitle(String title);

}