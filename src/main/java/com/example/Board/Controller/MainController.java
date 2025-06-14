package com.example.Board.Controller;

import com.example.Board.Dto.Members;
import com.example.Board.Entity.Board;
import com.example.Board.Entity.Member;
import com.example.Board.Enum.Sex;
import com.example.Board.Service.BoardService;
import com.example.Board.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
public class MainController {
    private final BoardService boardService;
    private final MemberService memberService;
    @Autowired
    public MainController(BoardService boardService, MemberService memberService) {
        this.boardService = boardService;
        this.memberService = memberService;
    }

    @GetMapping
    public String home(){
        return "index";
    }

    //회원가입.
    @GetMapping("/member/create")
    public String createMember(Model model){
        model.addAttribute("member",new Member());
        return"member/createMember";
    }

    //회원가입 완료;
    @PostMapping("/member/create")
    public String createMemberSuccess(@ModelAttribute("member") Members members, Model model) {
        if (memberService.CheckEmail(members.getMemberEmail())) {
            model.addAttribute("errorField", "email");
            model.addAttribute("errorMessage", "❌ 이미 사용 중인 이메일입니다.");
            return "member/createMember";
        }
        members.setSex(Sex.BOY);
        memberService.save(members);
        return "redirect:/login";
    }

}
