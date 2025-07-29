package com.example.postservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Votes", schema = "dbo")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "PostId", nullable = false)
    private Integer postId;

    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "BountyAmount")
    private Integer bountyAmount;

    @Column(name = "VoteTypeId", nullable = false)
    private Integer voteTypeId;

    @Column(name = "CreationDate", nullable = false)
    private Instant creationDate;

}