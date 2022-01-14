package com.example.blogapplication.controller;

import com.example.blogapplication.POJO.LoginPojo;
import com.example.blogapplication.model.Person;
import com.example.blogapplication.repository.UserRepository;
import com.example.blogapplication.service.serviceImplementation.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class LoginController {

    private final UserServiceImp userServiceImp;

    private final UserRepository userRepository;



    @Autowired
    public LoginController(UserServiceImp userServiceImp, UserRepository userRepository) {
        this.userServiceImp = userServiceImp;
        this.userRepository = userRepository;
    }


    @PostMapping("login")
    public String loginProcess(HttpServletRequest request, @RequestBody LoginPojo login){

        Boolean exists = userRepository.existsByEmail(login.getEmail());
        String password1 = userServiceImp.getPersonByEmail(login.getEmail()).getPassword();



        if(exists && password1.equals(login.getPassword())){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("id", userRepository.getPersonByEmail(login.getEmail()).getUser_id());
            httpSession.setAttribute("username", userRepository.getPersonByEmail(login.getEmail()).getUsername());
            return "welcome" + " " + userRepository.getPersonByEmail(login.getEmail()).getUsername();
        }
        return "not successful";
    }

    @PostMapping("/addPerson/{friend_id}")
    public String addFriend(HttpSession session, @PathVariable Long friend_id ){
        Long id = (Long) session.getAttribute("id");
        if(id == friend_id){
            return "You cannot add self";
        }
        userServiceImp.addFriend(id,friend_id);

        return "Friends added";
    }

    @GetMapping("/listOfFriends")
    public List<Person> listOfFriends(HttpSession session){
        Long id = (Long) session.getAttribute("id");
       return userServiceImp.getFriends(id);
    }

    @DeleteMapping("/deleteUser")
    public  String deleteUser(HttpSession session){
        Long id = (Long) session.getAttribute("id");
        userServiceImp.delete(id);
        return "user deleted";
    }

    @GetMapping("/logout")
    public String home( HttpSession session){
        Long id = (Long) session.getAttribute("id");
        Person person = userRepository.getById(id);
        session.invalidate();
        return person.getUsername() + " is" + " logged out";
    }
}
