package com.example.blogapplication.repository;


import com.example.blogapplication.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Person,Long> {
//    Person getPersonByEmailAndPassword(String email, String password);

    List<Person> getAllByEmail(String email);

    Person getPersonByEmail(String email);

    Boolean existsByEmail(String email);


}
