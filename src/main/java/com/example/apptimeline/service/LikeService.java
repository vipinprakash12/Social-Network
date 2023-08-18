package com.example.apptimeline.service;

import com.example.apptimeline.dto.Post;
import com.example.apptimeline.dto.User;
import com.example.apptimeline.dto.Like;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LikeService {

    private Map<Long, List<User>> postLikes = new HashMap<>();

    public List<User> getLikesForPost(Long postId) {
        return postLikes.getOrDefault(postId, new ArrayList<>());
    }

    public boolean addLike(Long postId, User user) {
        List<User> likes = postLikes.computeIfAbsent(postId, k -> new ArrayList<>());
        if (likes.contains(user)) {
            return false; // User has already liked the post
        }
        likes.add(user);
        return true;
    }
}
