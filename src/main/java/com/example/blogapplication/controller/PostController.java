package com.example.blogapplication.controller;


import com.example.blogapplication.model.Person;
import com.example.blogapplication.model.Post;
import com.example.blogapplication.repository.UserRepository;
import com.example.blogapplication.service.serviceImplementation.PostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class PostController {


    private final PostServiceImp postServiceImp;

    private final UserRepository userRepository;

    @Autowired
    public PostController(PostServiceImp postServiceImp, UserRepository userRepository) {

        this.postServiceImp = postServiceImp;
        this.userRepository = userRepository;
    }


    @PostMapping("/createPost")
    public String createPost(@RequestBody Post post, HttpSession session){
        Long id = (Long) session.getAttribute("id");
        Person person = userRepository.getById(id);

        if(post.getDescription().length()== 0){
           return "Enter a post";
        }

        postServiceImp.createPost(post, person);
        return "post created";
    }

    @GetMapping("/allPosts")
    public List<Post> getAllPosts(){
       List<Post> post = postServiceImp.getAllOfPosts();
        System.out.println(post);
        return post;
    }

    @GetMapping("/getPostByUser")
    public List<Post> postByUser(HttpSession session){
        Long user_id = (Long) session.getAttribute("id");
        return postServiceImp.findPostByUser(user_id);
    }

    @PostMapping("/addPostToFavourite/{post_id}")
    public String addPostToFavourite(@PathVariable Long post_id, HttpSession session){
       Long user_id = (Long) session.getAttribute("id");
        postServiceImp.addPostToFavouriteList(user_id,post_id);
        return "post added to favourite";
    }

    @GetMapping("/favouritePosts")
    public  List<Post> getListOfFavouritePosts(HttpSession session){

        Long user_id = (Long) session.getAttribute("id");
        return postServiceImp.getListOfFavouritePosts(user_id);
    }

    @PostMapping("/likePost/{post_id}")
    public String likePost(@PathVariable Long post_id, HttpSession session){
        Long user_id = (Long) session.getAttribute("id");
        postServiceImp.addLikes(post_id,user_id);
        return "post has been liked";
    }

    @GetMapping("/getLikes/{post_id}")
    public  Integer getLikes(@PathVariable Long post_id){
        return postServiceImp.getLikes(post_id);
    }

    @DeleteMapping("/deletePost/{id}")
    public  String deletePost(@PathVariable("id") Long id, HttpSession session){
        Long id2 = (Long) session.getAttribute("id");
        Person person = userRepository.getById(id2);

        if (person == null){
            return "can not delete post";
        }

        Post post = postServiceImp.getPostById(id);
        postServiceImp.deletePost(post);
        return "post deleted";
    }

}
