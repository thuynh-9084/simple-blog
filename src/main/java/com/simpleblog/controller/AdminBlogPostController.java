package com.simpleblog.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.simpleblog.model.BlogPosts;
import com.simpleblog.service.BlogPostsService;

@Controller
public class AdminBlogPostController {

    @Autowired
    private BlogPostsService blogPostsService;
    
    @GetMapping("/dashboard")
    public String getAllPost(Model model) {
        model.addAttribute("getAllBlogPosts", blogPostsService.findAllPosts());
        model.addAttribute("blogPosts", new BlogPosts("","",LocalDate.now()));
        return "/dashboard";
    }

    @PostMapping("/dashboard")
    public String createPost(@ModelAttribute BlogPosts post, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("blogPosts", blogPostsService.findAllPosts());
            return "/dashboard";
        }
        post.setLocalDate(LocalDate.now());
        blogPostsService.createPost(post);
        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/{id}")
    public String deletePostById(@PathVariable Long id) {
        blogPostsService.deletePostById(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute BlogPosts updatedPost, Model model) throws Exception {
        blogPostsService.updatePost(id, updatedPost);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/edit/{id}")
    public String getEditFormById(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("blogPosts", blogPostsService.findPostById(id));
        return "blog-post-edit";
    }
    
}
