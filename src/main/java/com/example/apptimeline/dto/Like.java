package com.example.apptimeline.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Like {

    private Long id;
    private User author;
    private Post post;

    public Like(User author, Post post) {
        this.author = author;
        this.post = post;
    }

}
