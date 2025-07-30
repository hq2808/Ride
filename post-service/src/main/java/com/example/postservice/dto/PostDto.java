package com.example.postservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.example.postservice.entity.Post}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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