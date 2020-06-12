package com.spring.cursospringboot.repository;

import com.spring.cursospringboot.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
