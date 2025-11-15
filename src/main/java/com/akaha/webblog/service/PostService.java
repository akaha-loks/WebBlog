package com.akaha.webblog.service;

import com.akaha.webblog.model.Post;
import com.akaha.webblog.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) { this.postRepository = postRepository; }

    public Iterable<Post> getPosts(){
        return postRepository.findAll();
    }
}
