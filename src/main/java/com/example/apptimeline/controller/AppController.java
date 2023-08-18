package com.example.apptimeline.controller;

import com.example.apptimeline.dto.Comment;
import com.example.apptimeline.dto.Group;
import com.example.apptimeline.dto.Post;
import com.example.apptimeline.dto.User;
import com.example.apptimeline.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private PostService postService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/feed")
    public ResponseEntity<List<Post>> getFeed(@RequestParam Long userId) {
        User user = userService.getUser(userId);
        log.info("User {}",user.getUsername());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Group> userGroups = groupService.getGroupsByUser(user);
        log.info("User groups {}",userGroups);
        List<Post> feedPosts = postService.getPostsFromGroups(userGroups);
        log.info("Feed posts {}",feedPosts);

        for (Post post : feedPosts) {
            List<User> likes = likeService.getLikesForPost(post.getId());
            List<Comment> comments = commentService.getCommentsForPost(post.getId());
            post.setLikes(likes);
            post.setComments(comments);
        }

        return ResponseEntity.ok(feedPosts);
    }

    @GetMapping("/group/{groupId}/posts")
    public ResponseEntity<List<Post>> getGroupPosts(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }

        List<Post> groupPosts = postService.getPostsFromGroup(group);
        for (Post post : groupPosts) {
            List<User> likes = likeService.getLikesForPost(post.getId());
            List<Comment> comments = commentService.getCommentsForPost(post.getId());
            post.setLikes(likes);
            post.setComments(comments);
        }
        return ResponseEntity.ok(groupPosts);
    }

    @PostMapping("/group/{groupId}/posts")
    public ResponseEntity<String> createGroupPost(@PathVariable Long groupId, @RequestBody Post post,@RequestParam Long userId) {
        Group group = groupService.getGroupById(groupId);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }

        User author = userService.getUser(userId);
        post.setGroup(group);
        post.setAuthor(author);
        postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/post/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId, @RequestParam Long userId) {
        User user = userService.getUser(userId);
        Post post = postService.getPostById(postId);
        if (post == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        if (likeService.addLike(postId, user)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.badRequest().body("User already liked the post.");
        }
    }

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<String> commentOnPost(@PathVariable Long postId, @RequestBody Comment comment, @RequestParam Long userId) {
        User user = userService.getUser(userId);
        Post post = postService.getPostById(postId);
        if (post == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        comment.setAuthor(user);
        commentService.addComment(postId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/group")
    public ResponseEntity<Group> addGroup(@RequestBody Group group) {
        groupService.addGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

}
