package com.example.lab15.controllers;

import com.example.lab15.models.Post;
import com.example.lab15.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogContraller {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String getBlog(Model model){
        Iterable<Post> iterable = postRepository.findAll();
        model.addAttribute("posts",iterable);
        return "blog";
    }
}