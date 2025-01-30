package com.simpleblog.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.simpleblog.model.BlogPosts;
import com.simpleblog.service.BlogPostsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/blog")
    public String createPost(@ModelAttribute BlogPosts post, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("blogPosts", blogPostsService.findAllPosts());
            return "blog";
        }
        post.setLocalDate(LocalDate.now());
        blogPostsService.createPost(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String getPostById(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("blogPosts", blogPostsService.findPostById(id));
        return "blog-post-details";
    }

    @PostMapping("/blog/{id}")
    public String deletePostById(@PathVariable Long id) {
        blogPostsService.deletePostById(id);
        return "redirect:/blog";
    }

    @PostMapping("/blog/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute BlogPosts updatedPost, Model model) throws Exception {
        blogPostsService.updatePost(id, updatedPost);
        return "redirect:/blog";
    }

    @GetMapping("/blog/edit/{id}")
    public String getEditFormById(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("blogPosts", blogPostsService.findPostById(id));
        return "blog-post-edit";
    }

}
