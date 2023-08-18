package com.example.apptimeline.service;

import com.example.apptimeline.dto.Group;
import com.example.apptimeline.dto.Post;
import com.example.apptimeline.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostService {
    private List<Post> posts = new ArrayList<>();

    public void createPost(Post post) {
        posts.add(post);
    }

    public Post getPostById(Long postId) {
        return posts.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElse(null);
    }

    public List<Post> getPostsFromGroups(List<Group> groups) {
        log.info("Posts {}",posts);
        log.info("Groups {}",groups);
        return posts.stream()
                .filter(post -> groups.contains(post.getGroup()))
                .collect(Collectors.toList());
    }

    public List<Post> getPostsFromGroup(Group group) {
        return posts.stream()
                .filter(post -> post.getGroup().equals(group))
                .collect(Collectors.toList());
    }

    public List<User> getLikesForPost(Post post) {
        return post.getLikes();
    }
}
