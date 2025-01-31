package com.simpleblog.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.simpleblog.model.BlogPosts;
import com.simpleblog.service.BlogPostsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlogPostController {
    
    @Autowired
    private BlogPostsService blogPostsService;

    @GetMapping("/blog")
    public String getAllPost(Model model) {
        model.addAttribute("getAllBlogPosts", blogPostsService.findAllPosts());
        model.addAttribute("blogPosts", new BlogPosts("","",LocalDate.now()));
        return "blog";
    }

    @GetMapping("/blog/{id}")
    public String getPostById(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("blogPosts", blogPostsService.findPostById(id));
        return "blog-post-details";
    }

}
