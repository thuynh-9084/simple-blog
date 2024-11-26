package com.simpleblog.controller;

import com.simpleblog.model.Post;
import com.simpleblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/home")
    public String getAllPost(Model model) {
        model.addAttribute("Posts",postService.findAllPosts());
        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/home")
    public String createPost(String title, String content,Model model){
        postService.createPost(title,content);
        model.addAttribute("Posts", postService.findAllPosts());
        return "redirect:/home";
    }

    @GetMapping("/home/{id}")
    public String getPostById(@PathVariable Long id, Model model) throws Exception {
        Post post = postService.findPostById(id);
        model.addAttribute("Post", post);
        return "/home/{id}";
    }

    @DeleteMapping("/home/{id}")
    public String deletePostById(@PathVariable Long id, Model model) {
        postService.deletePostById(id);
        model.addAttribute("Posts",postService.findAllPosts());
        return "redirect:/home";
    }

}
