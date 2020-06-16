package com.spring.cursospringboot.controller;

import com.spring.cursospringboot.model.Post;
import com.spring.cursospringboot.service.PostService;
import net.bytebuddy.description.type.TypeDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    /* Diretório da página HTML e método HTTP */
    @RequestMapping(value = "/postspage", method = RequestMethod.GET)
    public ModelAndView getPosts() {

        /* Cria a view, o arquivo html precisa ter o mesmo nome */
        ModelAndView mv = new ModelAndView("posts");

        /* Traz todos os posts do bd */
        List<Post> posts = postService.findAll();

        /* Relaciona variável view com o model (objetos do java) */
        mv.addObject("posts", posts);

        return mv;
    }

    @RequestMapping(value = "/postspage/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id) {

        /* Cria a view, o arquivo html precisa ter o mesmo nome */
        ModelAndView mv = new ModelAndView("postsDetails");

        /* Traz todos o post do bd */
        Post post = postService.findById(id);

        /* Relaciona variável view com o model (objeto do java) */
        mv.addObject("post", post);

        return mv;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String getNewPost() {
        return "/newpost";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String saveNewPost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {

        /* Se houver erros de validação, redireciona para a mesma página */
        if (result.hasFieldErrors()) {
            attributes.addFlashAttribute("mensagem", "Algum campo está em branco");
            return "redirect:/newpost";
        }

        post.setDate(LocalDate.now());
        postService.save(post);

        /* Salva e redireciona para os posts */
        return "redirect:/postspage";


    }

}
