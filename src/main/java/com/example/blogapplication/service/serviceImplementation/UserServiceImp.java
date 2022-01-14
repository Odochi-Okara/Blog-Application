package com.example.blogapplication.service.serviceImplementation;


import com.example.blogapplication.model.Person;
import com.example.blogapplication.repository.UserRepository;
import com.example.blogapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Person> findPersonsByEmail(String email) {

        List<Person> people = userRepository.getAllByEmail(email);
        return people;
    }

    @Override
    public boolean createUser(Person person) {
        boolean flag = false;
        Person person1 = userRepository.getPersonByEmail(person.getEmail());
         if(person1 == null){
             userRepository.save(person);
             flag = true;
             return flag;
         }
         return flag;
    }

    @Override
    public void delete (Long user_id){
        Person person = userRepository.getById(user_id);
        userRepository.delete(person);
    }


    @Override
    public Person getPersonByEmail(String email) {
        return userRepository.getPersonByEmail(email);
    }

    @Override
    public String addFriend(Long user_id, Long friend_id) {

        if(user_id.equals(friend_id)){
            return "You cannot add yourself";
        }

        if(userRepository.findById(friend_id).isEmpty()){
            return "user not found";
        }

        Person person = userRepository.getById(user_id);

        Person friend= userRepository.getById(friend_id);

        person.getFriends().add(friend);
        friend.getFriends().add(person);
        userRepository.save(person);
        userRepository.save(friend);
        return person.getUsername() + " " + "is now friends with " + friend.getUsername();
    }

    @Override
    public List<Person> getFriends(Long user_id) {
        Person person = userRepository.getById(user_id);
        return person.getFriends();
    }


}
