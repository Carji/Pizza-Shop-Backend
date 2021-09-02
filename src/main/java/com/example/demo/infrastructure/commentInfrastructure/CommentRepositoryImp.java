package com.example.demo.infrastructure.commentInfrastructure;

import java.util.*;
import com.example.demo.domain.commentDomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImp implements CommentRepositoryWrite{
    private final CommentRepositoryJPA commentRepositoryJPA;

    @Autowired
    public CommentRepositoryImp(final CommentRepositoryJPA commentRepositoryJPA){
        this.commentRepositoryJPA = commentRepositoryJPA;
    }
    @Override
    public void add(Comment comment) {
        this.commentRepositoryJPA.save(comment);      
    }
    @Override
    public Optional<Comment> findById(UUID id) {
        return this.commentRepositoryJPA.findById(id);
    }
    @Override
    public boolean exists(String field) {
        return this.commentRepositoryJPA.exists(field);
    }
}