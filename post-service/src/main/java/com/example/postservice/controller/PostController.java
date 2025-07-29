package com.example.postservice.controller;

import com.example.postservice.dto.PostDto;
import com.example.postservice.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostController {
    final PostService postService;

    @GetMapping
    public List<PostDto> getAllUsers() {
        return postService.findAll();
    }

    @GetMapping("/test")
    public String test() {
        return "postService.findAll()";
    }
}
