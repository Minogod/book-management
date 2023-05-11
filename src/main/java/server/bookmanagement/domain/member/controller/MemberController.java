package server.bookmanagement.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bookmanagement.domain.member.dto.MemberDto;
import server.bookmanagement.domain.member.entity.Member;
import server.bookmanagement.domain.member.mapper.MemberMapper;
import server.bookmanagement.global.dto.SingleResponseDto;
import server.bookmanagement.domain.member.service.MemberService;

import javax.transaction.Transactional;

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
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("{member-id}")
    public ResponseEntity<?> deleteMember(@PathVariable("member-id") long memberId) {
        Member member = memberService.findById(memberId);
        Member deletedMember = memberService.deleteMember(member);
        MemberDto.Response response = memberMapper.memberToMemberResponse(deletedMember);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}
