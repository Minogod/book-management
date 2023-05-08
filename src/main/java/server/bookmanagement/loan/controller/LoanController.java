package server.bookmanagement.loan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bookmanagement.library.inventory.entity.LibraryInventory;
import server.bookmanagement.library.inventory.service.LibraryInventoryService;
import server.bookmanagement.loan.dto.LoanDto;
import server.bookmanagement.loan.entity.Loan;
import server.bookmanagement.loan.mapper.LoanMapper;
import server.bookmanagement.loan.service.LoanService;
import server.bookmanagement.member.entity.Member;
import server.bookmanagement.member.service.MemberService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {
    private final MemberService memberService;
    private final LibraryInventoryService libraryInventoryService;
    private final LoanService loanService;
    private final LoanMapper loanMapper;
    @Transactional
    @PostMapping
    public ResponseEntity<?> loanBook(@RequestBody LoanDto.Post post) {
        //회원-> 도서관에 등록된 책(LibraryBook) 대여
        Member member = memberService.findById(post.getMemberId());
        //Todo: 사용자 대출가능여부 확인 코드 필요
        LibraryInventory libraryInventory = libraryInventoryService.findById(post.getLibraryInventoryId());
        //Todo: 도서관에 등록된 책이 대여가능 상태인지 확인 코드 필요 (재고수량 확인)
        Loan loan = loanMapper.LoanPostToLoan(post);
        loan.setLoanedAt(LocalDateTime.now()); //대여한시간
        loan.setMember(member);
        loan.setLibraryInventory(libraryInventory);
        libraryInventoryService.setLoanQuantity(libraryInventory); // 대여한 재고 수정
        Loan loanBook = loanService.loanBook(loan);
        LoanDto.Response response = loanMapper.LoanToLoanResponse(loanBook);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("{loan-id}/return")
    public ResponseEntity<?> returnBook(@RequestBody LoanDto.Post post, @PathVariable("loan-id") long loanId) {
        Member member = memberService.findById(post.getMemberId());
        LibraryInventory libraryInventory = libraryInventoryService.findById(post.getLibraryInventoryId());
        Loan loan = loanService.findById(loanId);
        //Todo: 해당 회원이 빌린 책이 맞는지 확인 필요
        //Todo: 날짜 확인후 연체 여부 및 패널티 부여
        Loan returnBook = loanService.returnBook(loan);
        LoanDto.Response response = loanMapper.LoanToLoanResponse(returnBook);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
