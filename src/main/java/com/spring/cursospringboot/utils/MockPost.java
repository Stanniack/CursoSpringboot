package com.spring.cursospringboot.utils;

import com.spring.cursospringboot.model.Post;
import com.spring.cursospringboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class MockPost {

    @Autowired
    PostRepository postRepository;

    /* Método é automaticamente chamada quando a aplicação for chamada (tipo um main) */
    @PostConstruct
    public void savePosts() {
        Post p1 = new Post("Aprendendo Spring Boot",
                "Mateus", "Como entender spring boot em 1 semana", LocalDate.now());

        Post p2 = new Post("Curso Springboot",
                "Mateus", "Como entender spring boot em 3 dias", LocalDate.now());

        postRepository.save(p1);
        postRepository.save(p2);
    }
}
