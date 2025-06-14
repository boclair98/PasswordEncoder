package com.example.Board.Repository;

import com.example.Board.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    boolean existsByMemberEmail(String email);

    boolean existsByMemberName(String name);
}
