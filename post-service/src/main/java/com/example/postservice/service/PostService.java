package com.example.postservice.service;

import com.example.postservice.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();
}
