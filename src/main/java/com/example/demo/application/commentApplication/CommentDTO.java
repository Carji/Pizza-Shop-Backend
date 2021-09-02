package com.example.demo.application.commentApplication;

import java.util.*;
import lombok.*;

public @Getter @Setter @NoArgsConstructor class CommentDTO {
    public UUID id;
    public String text;
//    public Date date;
    public int score;
    public UUID user;
    public UUID pizza;
}