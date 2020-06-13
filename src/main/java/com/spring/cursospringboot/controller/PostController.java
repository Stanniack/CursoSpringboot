package com.spring.cursospringboot.controller;

import com.spring.cursospringboot.model.Post;
import com.spring.cursospringboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    /* Diretório da página HTML e método HTTP */
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts () {

        /* Cria a view, o arquivo html precisa ter o mesmo nome */
        ModelAndView mv = new ModelAndView("posts");

        /* Traz todos os posts do bd */
        List<Post> posts = postService.findAll();

        /* Relaciona view com o model (objetos do java) */
        mv.addObject("posts", posts);

        return mv;
    }

}
