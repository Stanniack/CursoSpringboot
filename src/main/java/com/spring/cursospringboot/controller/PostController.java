package com.spring.cursospringboot.controller;

import com.spring.cursospringboot.model.Post;
import com.spring.cursospringboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/postdetail/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id) {

        /* Cria a view, o arquivo html precisa ter o mesmo nome */
        ModelAndView mv = new ModelAndView("postsDetails");

        /* Traz o post do bd */
        Post post = postService.findById(id);

        /* Relaciona variável view com o model (objeto do java) */
        mv.addObject("post", post);

        return mv;
    }

    @RequestMapping(value = "/editpost/{id}", method = RequestMethod.GET)
    public ModelAndView editPost(@PathVariable("id") long id) {

        /* Cria a view, o arquivo html precisa ter o mesmo nome */
        ModelAndView mv = new ModelAndView("editpost");

        /* Traz o post do bd */
        Post post = postService.findById(id);

        /* Relaciona variável view com o model (objeto do java) */
        mv.addObject("post", post);

        return mv;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String getNewPost() {
        return "/newpost";
    }

    @RequestMapping(value = "/editpost", method = RequestMethod.GET)
    public String getEditPost() {
        return "/editpost";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String saveNewPost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {

        /* Se houver erros de validação, redireciona para a mesma página */
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Algum campo está em branco");
            return "redirect:/newpost";
        }

        post.setDate(LocalDate.now());
        postService.save(post);

        /* Salva e redireciona para os posts */
        return "redirect:/postspage";

    }

    @RequestMapping(value = "/editpost", method = RequestMethod.POST)
    public String updatePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {

        /* Se houver erros de validação, redireciona para a mesma página */
        if (post.getTitulo().equals("") || post.getAutor().equals("") || post.getTexto().equals("")) {
            attributes.addFlashAttribute("mensagem", "Algum campo está em branco");
            /* Dando pau */
            return "redirect:/editpost";
        }

        post.setDate(LocalDate.now());
        /* Atualizar */
        //postService.save(post);

        /* Atualiza e redireciona para os posts */
        return "redirect:/postspage";

    }

    @RequestMapping(value = "/remove")
    public String removePost (Long idPost, RedirectAttributes attributes) {

        Post post = new Post();
        post.setId(idPost);
        postService.delete(post);

        attributes.addFlashAttribute("msgRemove","Post removido.");

        return "redirect:/postspage";
    }

}
