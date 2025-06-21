package com.example.Board.Controller;

import com.example.Board.Dto.Members;
import com.example.Board.Entity.Board;
import com.example.Board.Entity.Member;
import com.example.Board.Enum.LoginResult;
import com.example.Board.Enum.Sex;
import com.example.Board.Service.BoardService;
import com.example.Board.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.ws.Response;
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
        return "redirect:/login";
    }

    @GetMapping("/member/login")
    public String login(){
        return "member/login";
    }

    @PostMapping("/member/login")
    @ResponseBody
    public ResponseEntity<String> loginW(@RequestParam("email") String email,
                                 @RequestParam("password") String password){
        LoginResult result = memberService.login(email, password);
        if (result == LoginResult.SUCCESS) {
            return ResponseEntity.ok("로그인 성공!");
        } else if (result == LoginResult.EMAIL_NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디가 틀렸어요");
        } else if (result == LoginResult.PASSWORD_MISMATCH) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 틀렸어요");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알 수 없는 오류입니다.");
        }
    }

}
