package com.example.blogapplication.controller;



import com.example.blogapplication.model.Person;
import com.example.blogapplication.repository.UserRepository;
import com.example.blogapplication.service.serviceImplementation.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class SignupController {

    private final UserServiceImp userServiceImp;
    private final UserRepository userRepository;


    @Autowired
    public SignupController(UserServiceImp userServiceImp, UserRepository userRepository) {
        this.userServiceImp = userServiceImp;
        this.userRepository = userRepository;
    }


    @PostMapping("/signup")
    public String signup(@RequestBody Person person, HttpSession session){


    if(userServiceImp.createUser(person)){
        session.setAttribute("id", userRepository.getById(person.getUser_id()));
        return "user saved";
    }

        return "user exists";

    }
}
