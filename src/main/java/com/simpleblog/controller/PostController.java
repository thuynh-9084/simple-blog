package com.simpleblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.simpleblog.model.Post;
import com.simpleblog.service.PostService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    
    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public String getAllPost(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        model.addAttribute("post", new Post());
        return "post";
    }

    @PostMapping("/post")
    public String createPost(@ModelAttribute Post post, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("post", postService.findAllPosts());
            return "post";
        }
        postService.createPost(post);
        return "post";
    }

    @GetMapping("/post/{id}")
    public String getPostById(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("post", postService.findPostById(id));
        return "post-details";
    }

}
