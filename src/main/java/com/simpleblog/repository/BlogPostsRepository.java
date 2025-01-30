package com.simpleblog.repository;

import com.simpleblog.model.BlogPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPosts,Long> {
}
