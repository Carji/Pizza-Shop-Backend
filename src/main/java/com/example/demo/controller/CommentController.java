package com.example.demo.controller;

import java.util.UUID;
import javax.validation.Valid;
import com.example.demo.application.commentApplication.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    private final CommentApplication commentApplication;

    @Autowired
    public CommentController(CommentApplication commmentApplication) {
        this.commentApplication = commmentApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody @Valid final CreateDTOComment dto) {
        CommentDTO commentDTO = this.commentApplication.add(dto);
        return ResponseEntity.status(201).body(commentDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> get(@Valid @PathVariable UUID id) {
        CommentDTO commentDTO = this.commentApplication.get(id);
        return ResponseEntity.ok(commentDTO);
    }
}