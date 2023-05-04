package server.bookmanagement.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.bookmanagement.member.dto.MemberDto;
import server.bookmanagement.member.entity.Member;
import server.bookmanagement.member.mapper.MemberMapper;
import server.bookmanagement.member.service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberMapper memberMapper;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberDto.Post post){
        Member member = memberMapper.memberPostToMember(post);
        Member createdMember = memberService.createMember(member);
        MemberDto.Response response = memberMapper.memberToMemberResponse(createdMember);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
