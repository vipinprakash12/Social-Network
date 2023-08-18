package com.example.apptimeline.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private Long id;
    private String content;
    private User author;
}
