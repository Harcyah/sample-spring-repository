package com.harcyah.sample.spring.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDetails {

    private final Long postId;
    private final String postTitle;
    private final String commentContent;

}
