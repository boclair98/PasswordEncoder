package com.example.Board.Controller;

import com.example.Board.Entity.Board;
import com.example.Board.Entity.Member;
import com.example.Board.Repository.BoardRepository;
import com.example.Board.Service.BoardService;
import com.example.Board.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    public BoardController(BoardService boardService, MemberService memberService) {
        this.boardService = boardService;
        this.memberService = memberService;
    }

    @GetMapping("/new/write")
    public String boardSite(Model model, HttpSession session){
        if(session.getAttribute("loginMember") == null){
            return "member/login";
        }
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

    @GetMapping("/board/list")
    public String boardList(Model model){
        List<Board> boardList = boardService.findAll();
        model.addAttribute("boardList",boardList);
        return "board/boardlist";
    }

    @GetMapping("/view/{id}")
    public String boardView(@PathVariable("id") Long id,HttpSession session, Model model){
        Member loginMember = (Member) session.getAttribute("loginMember");
        Board board = boardService.findByBoardId(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));
        if(loginMember!=null && loginMember.getId().equals(board.getMember().getId())){
            model.addAttribute("board",board);
            return "board/boardView";
        }
        return"";
    }
}
