package com.mongodb.spring.resources;

import com.mongodb.spring.domain.Post;
import com.mongodb.spring.domain.User;
import com.mongodb.spring.dto.UserDTO;
import com.mongodb.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping(value = "/users")
public class UserResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> userList = userService.findAll();
        List<UserDTO> userDTOList = userList.stream()
                .map(x -> new UserDTO(x))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(userDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user.getPostList());
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user) {
        userService.insert(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) {
        User obj = userService.fromDTO(objDTO);
        obj.setId(id);
        obj = userService.update(obj);
        return ResponseEntity.noContent().build();
    }
}