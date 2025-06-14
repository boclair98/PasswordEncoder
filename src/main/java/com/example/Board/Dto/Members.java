package com.example.Board.Dto;

import lombok.Data;

@Data
public class Members {
    private Long id;
    private String memberEmail;
    private String memberName;
    private String password;
    private int age;
}
