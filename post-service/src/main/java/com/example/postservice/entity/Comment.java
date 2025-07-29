package com.example.postservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Comments", schema = "dbo")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "CreationDate", nullable = false)
    private Instant creationDate;

    @Column(name = "PostId", nullable = false)
    private Integer postId;

    @Column(name = "Score")
    private Integer score;

    @Nationalized
    @Column(name = "Text", nullable = false, length = 700)
    private String text;

    @Column(name = "UserId")
    private Integer userId;

}