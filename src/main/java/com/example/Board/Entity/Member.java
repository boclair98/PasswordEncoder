package com.example.Board.Entity;

import com.example.Board.Enum.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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

    @Column(name ="member_sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "member_age")
    private int age;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Board> boardLists = new ArrayList<>();
    public void addBoard(Board board){
        boardLists.add(board);
        board.setMember(this);
    }


}
