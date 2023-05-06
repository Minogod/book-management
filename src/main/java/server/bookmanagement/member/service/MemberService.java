package server.bookmanagement.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.bookmanagement.global.error.exception.BusinessLogicException;
import server.bookmanagement.global.error.exception.ExceptionCode;
import server.bookmanagement.member.entity.Member;
import server.bookmanagement.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member createMember(Member member){
        return memberRepository.save(member);
    }
    public Member findById(long id){
        Optional<Member> optionalMember = memberRepository.findById(id);
        return optionalMember.orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }
}
