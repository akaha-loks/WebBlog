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

    public Post addPost(String title, String anons, String full_text){
        Post newPost = new Post(title, anons, full_text);
        if (newPost.getTitle() == null || newPost.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Пустое название!");
        }
        if(newPost.getFull_text() == null || newPost.getFull_text().isEmpty()){
            throw new IllegalArgumentException(newPost.getTitle() + ": не может быть пустым");
        }
        return postRepository.save(newPost);
    }

    public Post postDetails(long postId){
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Пост с таким id не существует!"));
    }

    public Post postEdit(long postId){
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Пост с таким id не существует!"));
    }

    public Post postUpdate(long postId, String title, String anons, String full_text){
        Post updatePost = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Пост с таким id не существует!"));
        if (updatePost.getTitle() == null || updatePost.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Пустое название!");
        }
        if(updatePost.getFull_text() == null || updatePost.getFull_text().isEmpty()){
            throw new IllegalArgumentException(updatePost.getTitle() + ": не может быть пустым");
        }
        updatePost.setTitle(title);
        updatePost.setAnons(anons);
        updatePost.setFull_text(full_text);
        return postRepository.save(updatePost);
    }

    public Post postDelete(long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Пост с таким id не существует!"));
        postRepository.delete(post);

        return post;
    }
}
