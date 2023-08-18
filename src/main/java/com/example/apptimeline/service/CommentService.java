package com.example.apptimeline.service;

import com.example.apptimeline.dto.Comment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    private Map<Long, List<Comment>> postComments = new HashMap<>();

    public List<Comment> getCommentsForPost(Long postId) {
        return postComments.getOrDefault(postId, new ArrayList<>());
    }

    public void addComment(Long postId, Comment comment) {
        postComments.computeIfAbsent(postId, k -> new ArrayList<>()).add(comment);
    }
}
