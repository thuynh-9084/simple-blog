package com.simpleblog.controller;

import com.simpleblog.model.Post;
import com.simpleblog.service.PostService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> posts = postService.findAllPosts();
        if(posts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post newPost){
        if(!(newPost.getTitle().isEmpty() && newPost.getContent().isEmpty())) {
            Post post = postService.createPost(new Post(newPost.getTitle(),newPost.getContent(),newPost.getLocalDate()));
            return new ResponseEntity<>(post,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) throws Exception {
        Post post = postService.findPostById(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Post> deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
