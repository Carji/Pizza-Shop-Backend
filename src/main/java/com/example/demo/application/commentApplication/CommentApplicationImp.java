package com.example.demo.application.commentApplication;

import java.util.UUID;
import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.commentDomain.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentApplicationImp extends ApplicationBase<Comment, UUID> implements CommentApplication {

    private final CommentRepositoryWrite commentRepositoryWrite;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public CommentApplicationImp(final CommentRepositoryWrite commentRepositoryWrite, final ModelMapper modelMapper,
            final Logger logger) {
        super((id) -> commentRepositoryWrite.findById(id));
        this.commentRepositoryWrite = commentRepositoryWrite;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public CommentDTO add(CreateDTOComment dto) {
        Comment comment = modelMapper.map(dto, Comment.class);
        comment.setId(UUID.randomUUID());
        comment.setPizzaId(dto.getPizza());
        comment.setUserId(dto.getUser());
        comment.validate();
        this.commentRepositoryWrite.add(comment);
        logger.info(serializeObject(comment, "inserted"));
        return this.modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO get(UUID id) {
        Comment comment = this.findById(id);
        logger.info(serializeObject(comment, "selected"));
        return this.modelMapper.map(comment, CommentDTO.class);
    }

}