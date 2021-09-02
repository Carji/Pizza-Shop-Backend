package com.example.demo.application.commentApplication;

import java.util.UUID;

public interface CommentApplication {
        public CommentDTO add(CreateDTOComment dto);
        public CommentDTO get(UUID id);
}
