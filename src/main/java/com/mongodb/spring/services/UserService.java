package com.mongodb.spring.services;

import com.mongodb.spring.domain.User;
import com.mongodb.spring.dto.UserDTO;
import com.mongodb.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User findById(String id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException());
    }

    public User insert(User user){
        return userRepository.insert(user);
    }

    /*public User formDTO(UserDTO userDTO){
        return new User(userDTO.getId(),userDTO.getName(),userDTO.getEmail());
    }*/

    public void deleteById(String id){
        findById(id);
        userRepository.deleteById(id);
    }

}
