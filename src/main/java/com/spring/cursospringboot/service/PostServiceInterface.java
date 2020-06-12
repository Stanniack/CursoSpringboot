package com.spring.cursospringboot.service;

import com.spring.cursospringboot.model.Post;

import java.util.List;

public interface PostServiceInterface {

    List<Post> findAll();
    Post findById(long id);
    Post save(Post post);
}
