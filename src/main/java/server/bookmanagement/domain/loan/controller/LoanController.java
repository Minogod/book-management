package server.bookmanagement.domain.loan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bookmanagement.domain.loan.entity.Loan;
import server.bookmanagement.domain.loan.mapper.LoanMapper;
import server.bookmanagement.global.dto.SingleResponseDto;
import server.bookmanagement.domain.library.inventory.entity.LibraryInventory;
import server.bookmanagement.domain.library.inventory.service.LibraryInventoryService;
import server.bookmanagement.domain.loan.dto.LoanDto;
import server.bookmanagement.domain.loan.service.LoanService;
import server.bookmanagement.domain.member.entity.Member;
import server.bookmanagement.domain.member.service.MemberService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
//@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {
    private final MemberService memberService;
    private final LibraryInventoryService libraryInventoryService;
    private final LoanService loanService;
    private final LoanMapper loanMapper;
    @Transactional
    @PostMapping("/loan")
    public ResponseEntity<?> loanBook(@RequestBody LoanDto.Post post) {
        //회원 -> 도서관에 등록된 책(LibraryBook) 대여
        Member member = memberService.findById(post.getMemberId());
        //Todo: 사용자가 대여가능한 상태인지 확인
        // 대여 조건1 : 대여 중인책이 5권 이하
        // 대여 조건2 : 연체되어서 패널티 안받고 있는지
        memberService.validLoanStatus(member);

        LibraryInventory libraryInventory = libraryInventoryService.findById(post.getLibraryInventoryId());
        //Todo: 도서관에 등록된 책이 대여가능 상태인지 확인 코드 필요 (재고수량 확인)
        libraryInventoryService.validLoanStatus(libraryInventory);

        Loan loan = loanMapper.LoanPostToLoan(post);
        loan.setLoanedAt(LocalDateTime.now()); //대여한시간
        loan.setMember(member);
        loan.setLibraryInventory(libraryInventory);
        //Todo: 대여후 도서관에 등록된 책 재고수량 수정 + 상태값 변경
        libraryInventoryService.addLoanQuantity(libraryInventory);

        Loan loanBook = loanService.loanBook(loan);
        LoanDto.Response response = loanMapper.LoanToLoanResponse(loanBook);
        //Todo: 이 요청에서 예외,오류 발생시 롤백 필요
        //Todo: 여기에서 Member 의 대여중인 책 수량 체크후 상태변경
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/return/{loan-id}")
    public ResponseEntity<?> returnBook(@RequestBody LoanDto.Post post, @PathVariable("loan-id") long loanId) {
        Member member = memberService.findById(post.getMemberId());
        LibraryInventory libraryInventory = libraryInventoryService.findById(post.getLibraryInventoryId());
        Loan loan = loanService.findById(loanId);
        //Todo: 해당 회원이 빌린 책이 맞는지 확인 필요
        //Todo: 날짜 확인후 연체 여부확인 및 패널티 부여 (연체된 날짜만큼 대여 불가능)
        Loan returnBook = loanService.returnBook(loan);
        LoanDto.Response response = loanMapper.LoanToLoanResponse(returnBook);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}
