package com.example.blogapplication.repository;



import com.example.blogapplication.model.Person;
import com.example.blogapplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
        Post getPostById(Long id);
        List<Post> findPostByPerson(Person person);
        List<Post> getAllByPerson(Long user_id);


}
