package com.simpleblog.service;

import com.simpleblog.model.BlogPosts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogPostsService {

    List<BlogPosts> findAllPosts();
    BlogPosts createPost(BlogPosts blogPost);
    void deletePostById(Long id);
    BlogPosts findPostById(Long id) throws Exception;
    BlogPosts updatePost(Long id, BlogPosts updatedBlogPost) throws Exception;
}
