package com.example.postservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "PostLinks", schema = "dbo")
public class PostLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "CreationDate", nullable = false)
    private Instant creationDate;

    @Column(name = "PostId", nullable = false)
    private Integer postId;

    @Column(name = "RelatedPostId", nullable = false)
    private Integer relatedPostId;

    @Column(name = "LinkTypeId", nullable = false)
    private Integer linkTypeId;

}