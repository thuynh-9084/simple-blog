package com.simpleblog.service.implementation;

import com.simpleblog.model.Post;
import com.simpleblog.repository.PostRepository;
import com.simpleblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public Post createPost(String title ,String content) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String timestamp = localDateTime.format(dateTimeFormatter);
        Post post = new Post(title,content,timestamp);
        postRepository.save(post);
        return post;
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
