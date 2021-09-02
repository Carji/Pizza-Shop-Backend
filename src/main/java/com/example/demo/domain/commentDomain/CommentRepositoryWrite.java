package com.example.demo.domain.commentDomain;

import java.util.UUID;
import com.example.demo.core.*;

public interface CommentRepositoryWrite extends FindById<Comment,UUID>, ExistsByField{
    public void add(Comment comment);
}