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
    public String blogMain(Model model){
        model.addAttribute("posts", postService.getPosts());
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        postService.addPost(title, anons, full_text);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long postId, Model model){
        try {
            model.addAttribute("post", postService.postDetails(postId));
            return "blog-details";
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
            return "redirect:/blog";
        }
    }
}
