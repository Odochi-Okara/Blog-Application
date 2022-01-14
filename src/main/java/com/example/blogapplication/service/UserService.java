package com.example.blogapplication.service;


import com.example.blogapplication.model.Person;

import java.util.List;

public interface UserService {
    public boolean createUser(Person person);

    public void delete (Long user_id);

    public List<Person> findPersonsByEmail(String email);

    public Person getPersonByEmail(String email);

    public String  addFriend(Long user_id, Long friend_id);

    public List<Person> getFriends (Long user_id);
}
