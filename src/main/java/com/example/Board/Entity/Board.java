package com.example.Board.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    @Column(name = "board_localDateTime")
    @CreationTimestamp
    private LocalDateTime localDateTime;
    private String title;
    @Column(name ="board_text")
    private String content;
    @Column(name="board_password")
    private int password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
