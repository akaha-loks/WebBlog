package com.akaha.webblog.controller;

import com.akaha.webblog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BlogController {

    private final PostService postService;

    public BlogController(PostService postService) { this.postService = postService; }

    @GetMapping("/blog")
    public String getAllPosts(Model model){
        model.addAttribute("posts", postService.getAllPosts());
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String showCreatePostForm(){
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String createPost(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text){
        postService.createPost(title, anons, full_text);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String getPostDetails(@PathVariable(value = "id") long postId, Model model){
        try {
            model.addAttribute("post", postService.getPostById(postId));
            return "blog-details";
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
            return "redirect:/blog";
        }
    }

    @GetMapping("/blog/{id}/edit")
    public String showEditPostForm(@PathVariable(value = "id") long postId, Model model){
        model.addAttribute("post", postService.getPostById(postId));
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String updatePost(@PathVariable(value = "id") long postId, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text){
        postService.updatePost(postId, title, anons, full_text);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String deletePost(@PathVariable(value = "id") long postId){
        postService.deletePost(postId);
        return "redirect:/blog";
    }
}
