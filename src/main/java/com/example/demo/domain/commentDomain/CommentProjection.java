package com.example.demo.domain.commentDomain;

import java.util.UUID;

public interface CommentProjection {
    UUID getId();
    String getText();
    String getRating();
}