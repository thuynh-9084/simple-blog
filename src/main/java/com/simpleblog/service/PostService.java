package com.simpleblog.service;

import com.simpleblog.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    List<Post> findAllPosts();
    Post createPost(Post post);
    void deletePostById(Long id);
    Post findPostById(Long id) throws Exception;
}
