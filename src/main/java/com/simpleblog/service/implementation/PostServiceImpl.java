package com.simpleblog.service.implementation;

import com.simpleblog.model.Post;
import com.simpleblog.repository.PostRepository;
import com.simpleblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(Post post) {
        Post postCreation = new Post(post.getTitle(),post.getContent(),LocalDate.now());
        return postRepository.save(postCreation);
    }

    @Override
    public Post updatePost(Long id, Post updatedPost) throws Exception {
        Post existingPostById = findPostById(id);
        existingPostById.setTitle(updatedPost.getTitle());
        existingPostById.setContent(updatedPost.getContent());
        existingPostById.setLocalDate(LocalDate.now());
        return postRepository.save(existingPostById);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post findPostById(Long id) throws Exception {
        return postRepository.findById(id)
                .orElseThrow(()-> new Exception("No post found by that id."));
    }
}
