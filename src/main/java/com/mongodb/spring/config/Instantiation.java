package com.mongodb.spring.config;

import com.mongodb.spring.domain.Post;
import com.mongodb.spring.domain.User;
import com.mongodb.spring.repositories.PostRepository;
import com.mongodb.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com", "123");
        User alex = new User(null, "Alex Green", "alex@gmail.com", "222");
        User bob = new User(null, "Bob Grey", "bob@gmail.com", "666");

        Post post1 = new Post(null, new Date(), "Summer Feelings", "Viver durate o verão é ótiomo!", bob);
        Post post2 = new Post(null, new Date(), "Winter Cold", "O inverno está chegando", alex);
        Post post3 = new Post(null, new Date(), "Férias com a Familia", "Bora meter a festa do covid", bob);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.saveAll(Arrays.asList(post1,post2,post3));


    }
}
