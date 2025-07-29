package com.example.postservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Users", schema = "dbo")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Lob
    @Column(name = "AboutMe")
    private String aboutMe;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "CreationDate", nullable = false)
    private Instant creationDate;

    @Nationalized
    @Column(name = "DisplayName", nullable = false, length = 40)
    private String displayName;

    @Column(name = "DownVotes", nullable = false)
    private Integer downVotes;

    @Nationalized
    @Column(name = "EmailHash", length = 40)
    private String emailHash;

    @Column(name = "LastAccessDate", nullable = false)
    private Instant lastAccessDate;

    @Nationalized
    @Column(name = "Location", length = 100)
    private String location;

    @Column(name = "Reputation", nullable = false)
    private Integer reputation;

    @Column(name = "UpVotes", nullable = false)
    private Integer upVotes;

    @Column(name = "Views", nullable = false)
    private Integer views;

    @Nationalized
    @Column(name = "WebsiteUrl", length = 200)
    private String websiteUrl;

    @Column(name = "AccountId")
    private Integer accountId;

}