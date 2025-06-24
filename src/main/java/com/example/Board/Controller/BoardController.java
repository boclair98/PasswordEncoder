package com.example.Board.Controller;

import com.example.Board.Entity.Board;
import com.example.Board.Entity.Member;
import com.example.Board.Service.BoardService;
import com.example.Board.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/new/write")
    public String boardSite(Model model, HttpSession session){
        model.addAttribute("board",new Board());
        Member member = (Member) session.getAttribute("loginMember");
        model.addAttribute("loggedInMember",member);
        return"board/write";
    }

    @PostMapping("/new/write")
    public String wirteSuecces(@ModelAttribute("board") Board board,
                               HttpSession session){
        Member member = (Member) session.getAttribute("loginMember");
        board.setMember(member);
        board.setLocalDateTime(LocalDateTime.now());
        boardService.save(board);
        return"";
    }
}
