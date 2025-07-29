package com.example.postservice.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.example.postservice.entity.Post}
 */
@Value
public class PostDto implements Serializable {
    Integer id;
    Integer acceptedAnswerId;
    Integer answerCount;
    String body;
    Instant closedDate;
    Integer commentCount;
    Instant communityOwnedDate;
    Instant creationDate;
    Integer favoriteCount;
    Instant lastActivityDate;
    Instant lastEditDate;
    String lastEditorDisplayName;
    Integer lastEditorUserId;
    Integer ownerUserId;
    Integer parentId;
    Integer postTypeId;
    Integer score;
    String tags;
    String title;
    Integer viewCount;
}