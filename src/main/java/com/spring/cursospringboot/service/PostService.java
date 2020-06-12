package com.spring.cursospringboot.service;

import com.spring.cursospringboot.model.Post;
import com.spring.cursospringboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/* Indica que é um service */
@Service
public class PostService implements PostServiceInterface {

    /* Faz a injeção do repository */
    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
