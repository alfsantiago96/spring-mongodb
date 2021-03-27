package com.mongodb.spring.services;

import com.mongodb.spring.domain.User;
import com.mongodb.spring.dto.UserDTO;
import com.mongodb.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service //Regras de negócio e as Quatro operações basicas de CRUD
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

    //TODO
    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), "");
    }

    public void deleteById(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj) {
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return userRepository.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }



}
