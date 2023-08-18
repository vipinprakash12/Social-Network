package com.example.apptimeline.service;

import com.example.apptimeline.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    List<User> users = new ArrayList<>();

    public User getUser(Long userId) {
        for(User user: users){
            if (user.getId() == userId){
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
