package com.example.Board.Service;

import com.example.Board.Entity.Board;
import com.example.Board.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Board> findAll(){
        return boardRepository.findAll();
    }
    public Optional<Board> findByBoardId(Long id){
        return boardRepository.findById(id);
    }
}
