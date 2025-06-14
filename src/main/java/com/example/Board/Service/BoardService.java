package com.example.Board.Service;

import com.example.Board.Entity.Board;
import com.example.Board.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void save(Board board){
        boardRepository.save(board);
    }
}
