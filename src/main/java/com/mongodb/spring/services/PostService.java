package com.mongodb.spring.services;

import com.mongodb.spring.domain.Post;
import com.mongodb.spring.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    PostRepository postRepository;

    public List<Post> findAll(){
        List<Post> postList = postRepository.findAll();
        return postList;
    }

    public Post findById(String id){
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new RuntimeException());
    }

    public void deletedById(String id){
        findById(id);
        postRepository.deleteById(id);
    }

    public Post insert(Post newObj){
        return postRepository.insert(newObj);
    }

    public Post update(Post postBody){
        Post newObj = findById(postBody.getId());
        updateNewObj(newObj, postBody);
        return newObj;
    }

    public void updateNewObj(Post newObj, Post postBody){
        newObj.setUser(postBody.getUser());
        newObj.setTitle(postBody.getTitle());
        newObj.setBody(postBody.getBody());
        newObj.setDate(postBody.getDate());
    }


}
