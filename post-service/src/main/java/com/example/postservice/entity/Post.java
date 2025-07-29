package com.example.postservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Posts", schema = "dbo")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "AcceptedAnswerId")
    private Integer acceptedAnswerId;

    @Column(name = "AnswerCount")
    private Integer answerCount;

    @Nationalized
    @Lob
    @Column(name = "Body", nullable = false)
    private String body;

    @Column(name = "ClosedDate")
    private Instant closedDate;

    @Column(name = "CommentCount")
    private Integer commentCount;

    @Column(name = "CommunityOwnedDate")
    private Instant communityOwnedDate;

    @Column(name = "CreationDate", nullable = false)
    private Instant creationDate;

    @Column(name = "FavoriteCount")
    private Integer favoriteCount;

    @Column(name = "LastActivityDate", nullable = false)
    private Instant lastActivityDate;

    @Column(name = "LastEditDate")
    private Instant lastEditDate;

    @Nationalized
    @Column(name = "LastEditorDisplayName", length = 40)
    private String lastEditorDisplayName;

    @Column(name = "LastEditorUserId")
    private Integer lastEditorUserId;

    @Column(name = "OwnerUserId")
    private Integer ownerUserId;

    @Column(name = "ParentId")
    private Integer parentId;

    @Column(name = "PostTypeId", nullable = false)
    private Integer postTypeId;

    @Column(name = "Score", nullable = false)
    private Integer score;

    @Nationalized
    @Column(name = "Tags", length = 150)
    private String tags;

    @Nationalized
    @Column(name = "Title", length = 250)
    private String title;

    @Column(name = "ViewCount", nullable = false)
    private Integer viewCount;

}