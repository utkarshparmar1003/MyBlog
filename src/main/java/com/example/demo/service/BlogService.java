package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Blog;
public interface BlogService {

	public List<Blog> getAllPosts();

	public Optional<Blog> getPostById(int id);

	public void savePost(Blog post);

	public void deletePost(int id);
}
