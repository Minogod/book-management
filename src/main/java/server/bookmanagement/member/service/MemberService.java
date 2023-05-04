package server.bookmanagement.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.bookmanagement.member.entity.Member;
import server.bookmanagement.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member createMember(Member member){
        return memberRepository.save(member);
    }
}
