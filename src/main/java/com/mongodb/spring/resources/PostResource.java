package com.mongodb.spring.resources;

import com.mongodb.spring.domain.Post;
import com.mongodb.spring.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    PostService postService;

    @PostMapping
    public ResponseEntity<Post> insert(@RequestBody Post post){
        postService.insert(post);
        return ResponseEntity.ok().body(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deleteById(@PathVariable String id){
        postService.deletedById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        List<Post> postList = postService.findAll();
        return ResponseEntity.ok().body(postList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

}
