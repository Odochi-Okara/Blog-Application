package com.example.blogapplication.controller;


import com.example.blogapplication.model.Comment;
import com.example.blogapplication.model.Person;
import com.example.blogapplication.repository.UserRepository;
import com.example.blogapplication.service.serviceImplementation.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CommentController {
    private final CommentServiceImp commentService;
    private final UserRepository userRepository;

    @Autowired
    public CommentController(CommentServiceImp commentService, UserRepository userRepository) {
        this.commentService = commentService;
        this.userRepository = userRepository;
    }

    @PostMapping("/createComment/{id}")
    public String createComment (@PathVariable("id") Long postId, @RequestBody Comment comment, HttpSession session){
        Long id = (Long) session.getAttribute("id");
        Person person = userRepository.getById(id);

        if(person == null){
           return  "Cannot make comment";
        }

        comment.setPerson(person);


        if(commentService.createComment(comment,person.getUser_id(),postId)){
            session.setAttribute("id",person.getUser_id() );
            return person.getUsername()+" created a comment- "+ comment.getDescription();
        }
        return "comment not created";
    }
}
