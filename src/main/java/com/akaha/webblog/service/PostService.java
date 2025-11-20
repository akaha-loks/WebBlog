package com.akaha.webblog.service;

import com.akaha.webblog.model.Post;
import com.akaha.webblog.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) { this.postRepository = postRepository; }


    public Iterable<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void createPost(String title, String anons, String full_text){
        Post newPost = new Post(title, anons, full_text);

        validatePost(title, full_text);

        postRepository.save(newPost);
    }

    public Post getPostById(long postId){
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Пост с таким id не существует!"));
    }

    public void updatePost(long postId, String title, String anons, String full_text){
        Post updatePost = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Пост с таким id не существует!"));

        validatePost(title, full_text);

        updatePost.setTitle(title);
        updatePost.setAnons(anons);
        updatePost.setFull_text(full_text);
        postRepository.save(updatePost);
    }

    public void deletePost(long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Пост с таким id не существует!"));
        postRepository.delete(post);
    }


    private void validatePost(String title, String full_text){
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Пустое название!");
        }
        if(full_text == null || full_text.isEmpty()){
            throw new IllegalArgumentException("Содержимое статьи не может быть пустым");
        }
    }
}
