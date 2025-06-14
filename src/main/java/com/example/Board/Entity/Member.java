package com.example.Board.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name="member_name")
    private String memberName;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_age")
    private int age;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Board> boardLists = new ArrayList<>();
    public void addBoard(Board board){
        boardLists.add(board);
        board.setMember(this);
    }


}
