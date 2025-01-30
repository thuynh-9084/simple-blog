package com.simpleblog.service.implementation;

import com.simpleblog.model.BlogPosts;
import com.simpleblog.repository.BlogPostsRepository;
import com.simpleblog.service.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlogPostsServiceImpl implements BlogPostsService {

    @Autowired
    private BlogPostsRepository blogPostsRepository;

    @Override
    public List<BlogPosts> findAllPosts() {
        return blogPostsRepository.findAll();
    }

    @Override
    public BlogPosts createPost(BlogPosts blogPost) {
        BlogPosts blogPostCreation = new BlogPosts(blogPost.getTitle(),blogPost.getContent(),LocalDate.now());
        return blogPostsRepository.save(blogPostCreation);
    }

    @Override
    public BlogPosts updatePost(Long id, BlogPosts updatedBlogPost) throws Exception {
        BlogPosts existingPostById = findPostById(id);
        existingPostById.setTitle(updatedBlogPost.getTitle());
        existingPostById.setContent(updatedBlogPost.getContent());
        existingPostById.setLocalDate(LocalDate.now());
        return blogPostsRepository.save(existingPostById);
    }

    @Override
    public void deletePostById(Long id) {
        blogPostsRepository.deleteById(id);
    }

    @Override
    public BlogPosts findPostById(Long id) throws Exception {
        return blogPostsRepository.findById(id)
                .orElseThrow(()-> new Exception("No post found by that id."));
    }
}
