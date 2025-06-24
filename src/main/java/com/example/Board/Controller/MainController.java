package com.example.Board.Controller;

import com.example.Board.Dto.Members;
import com.example.Board.Entity.Board;
import com.example.Board.Entity.Member;
import com.example.Board.Enum.LoginResult;
import com.example.Board.Enum.Sex;
import com.example.Board.Service.BoardService;
import com.example.Board.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String createMemberSuccess(@ModelAttribute("member") Members members, Model model, HttpServletRequest request) {
        if (memberService.CheckEmail(members.getMemberEmail())) {
            model.addAttribute("errorField", "email");
            model.addAttribute("errorMessage", "❌ 이미 사용 중인 이메일입니다.");
            return "member/createMember";
        }
        String value = request.getParameter("sex");
        members.setSex(Sex.valueOf(value));
        memberService.save(members);
        return "redirect:/home/member/login";
    }
    
    @GetMapping("/member/login")
    public String login(){
        return "member/login";
    }

    @PostMapping("/member/login")
    public String loginW(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         HttpSession session,
                         Model model){
        Member member = memberService.login(email, password);

        if (member != null) {
            // 로그인 성공 시 세션에 로그인한 멤버 저장
            session.setAttribute("loginMember", member);
            model.addAttribute("member",member);
            return "member/s";
        } else {
            // 로그인 실패 (이메일 또는 비밀번호 오류)
            return "member/login";
        }
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
