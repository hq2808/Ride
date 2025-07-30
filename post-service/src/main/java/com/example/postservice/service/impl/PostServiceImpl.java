package com.example.postservice.service.impl;

import com.example.postservice.dto.PostDto;
import com.example.postservice.entity.Post;
import com.example.postservice.repository.PostRepository;
import com.example.postservice.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostServiceImpl implements PostService {
    final PostRepository postRepository;
    final ModelMapper modelMapper;

    @Override
    public List<PostDto> findAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> postLis = postRepository.findAll(pageable);
        return postLis.getContent().stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}
