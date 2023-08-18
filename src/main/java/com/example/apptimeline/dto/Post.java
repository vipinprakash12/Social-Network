package com.example.apptimeline.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Post {
    private Long id;
    private String content;
    private User author;
    private Group group;
    private List<User> likes = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
}
