package com.example.demo.service;

import com.example.demo.model.Blog;
import com.example.demo.repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllPosts() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getPostById(int id) {
        return blogRepository.findById(id);
    }

    public void savePost(Blog post) {
        blogRepository.save(post);
    }

    public void deletePost(int id) {
        blogRepository.deleteById(id);
    }
}
