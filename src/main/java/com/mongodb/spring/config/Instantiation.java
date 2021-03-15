package com.mongodb.spring.config;

import com.mongodb.spring.domain.User;
import com.mongodb.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;



    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com", "123");
        User alex = new User(null, "Alex Green", "alex@gmail.com", "222");
        User bob = new User(null, "Bob Grey", "bob@gmail.com", "666");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

    }
}
