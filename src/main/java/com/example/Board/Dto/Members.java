package com.example.Board.Dto;

import com.example.Board.Enum.Sex;
import lombok.Data;

@Data
public class Members {
    private Long id;
    private String memberEmail;
    private String memberName;
    private Sex sex;
    private String password;
    private int age;
}
