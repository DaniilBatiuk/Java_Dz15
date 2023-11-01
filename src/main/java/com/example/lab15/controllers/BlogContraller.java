package com.example.lab15.controllers;

import com.example.lab15.models.Post;
import com.example.lab15.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;

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

    @GetMapping("/blog/newPost")
    public String getNewPost(Model model){
        return "newPost";
    }

    @PostMapping("/blog/newPost")
    public String getNewPostToPost(@RequestParam String context,
                                   @RequestParam String header,
                                   Model model){
        Timestamp date = new Timestamp(new Date().getTime());
        Post post = new Post();
        post.setContext(context);
        post.setHeader(header);
        post.setDate(date);
        postRepository.save(post);
        return "home";
    }

    @GetMapping("/blog/{id}/update")
    public String postEdit(@PathVariable(value = "id") Long id, Model model){
        Post post = postRepository.findById(id).orElse(null);
        if(post != null){
            model.addAttribute("post", post);
        }
        return "updatePost";
    }

    @PostMapping("/blog/{id}/update")
    public String postUpdateSave(@PathVariable(value = "id") Long id,
                                 @RequestParam String context,
                                 @RequestParam String header,
                                 Model model){
        Post post = postRepository.findById(id).orElse(null);
        if(post != null){
            if(!context.equals(post.getContext())){
                post.setContext(context);
            }
            if(!header.equals(post.getHeader())) {
                post.setHeader(header);
            }
            postRepository.save(post);
        }
        return "home";
    }


    @GetMapping("/blog/{id}/remove")
    public String postRemove(@PathVariable(value = "id") Long id,
                                 Model model){
        postRepository.deleteById(id);
        return "redirect:/blog";
    }
}
