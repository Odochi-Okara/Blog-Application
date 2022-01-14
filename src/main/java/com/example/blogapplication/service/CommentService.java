package com.example.blogapplication.service;


import com.example.blogapplication.model.Comment;

public interface CommentService {

    public boolean createComment(Comment comment, Long person_id, Long post_id);
}
