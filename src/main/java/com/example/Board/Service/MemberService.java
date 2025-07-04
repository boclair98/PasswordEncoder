package com.example.Board.Service;

import com.example.Board.Dto.Members;
import com.example.Board.Entity.Board;
import com.example.Board.Entity.Member;
import com.example.Board.Enum.LoginResult;
import com.example.Board.Repository.BoardRepository;
import com.example.Board.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.logback.StructuredLogEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(Members members){
        Member member = new Member();
        member.setId(members.getId());
        member.setMemberEmail(members.getMemberEmail());
        member.setMemberName(members.getMemberName());
        String encodedPassword = passwordEncoder.encode(members.getPassword());
        member.setPassword(encodedPassword);
        member.setSex(members.getSex());
        member.setAge(members.getAge());
        memberRepository.save(member);
    }

    //중복 아이디
    public boolean CheckEmail(String email){
        return memberRepository.existsByMemberEmail(email);
    }

    //중복 이름.
    public boolean chekName(String memberName) {
        return memberRepository.existsByMemberName(memberName);
    }

    public Optional<Member> findId(Long id){
        return memberRepository.findById(id);
    }

    //멤버 로그인 검증.
    public Member login(String email, String password){
        Optional<Member> memberEmail = memberRepository.findByMemberEmail(email);
        if(memberEmail.isPresent()){
            Member member = memberEmail.get();
            if(passwordEncoder.matches(password,member.getPassword())){
                return member;
            }else{
                return null;
            }
        }
        return null;
    }
}
