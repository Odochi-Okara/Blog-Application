package com.example.blogapplication.POJO;


import com.example.blogapplication.model.Comment;
import com.example.blogapplication.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostPojo {

    private Long id;

    private String description;

    private Long personId;

    private Integer noOfLikes;

    private List<Person> postLikes;

    private List<Comment> comments;

}
