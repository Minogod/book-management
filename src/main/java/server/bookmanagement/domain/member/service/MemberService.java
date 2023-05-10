package server.bookmanagement.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import server.bookmanagement.domain.loan.entity.Loan;
import server.bookmanagement.domain.member.entity.Member;
import server.bookmanagement.domain.member.repository.MemberRepository;
import server.bookmanagement.global.error.exception.BusinessLogicException;
import server.bookmanagement.global.error.exception.ExceptionCode;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Value("${max_loan_quantity}")
    private long maxLoanQuantity;
    public Member createMember(Member member){
        return memberRepository.save(member);
    }
    public Member findById(long id){
        Optional<Member> optionalMember = memberRepository.findById(id);
        return optionalMember.orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public void validLoanStatus(Member member) {
        validLoanOverDue(member);
        validLoanQuantity(member);
    }

    public void validLoanQuantity(Member member) {
        //Todo: 사용자가 대여가능한 상태인지 확인
        // 대여 조건1 : 대여 중인책이 5권 이하
        // 대여 조건2 : 연체되어서 패널티 안받고 있는지 ( 이거는 LoanService 에서 구현 )
        if(isOverQuantity(member)) {
            throw new BusinessLogicException(ExceptionCode.EXCEEDS_MAXIMUM_QUANTITY);
        }
    }

    public void validLoanOverDue(Member member) {
        if(member.isOverDue()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_HAS_PENALTY);
        }
    }
    public boolean isOverQuantity(Member member) {
        List<Loan> loanBooks = member.getLoanBooks();
        long loanNum = 0;

        for(Loan loan : loanBooks) {
            Loan.LoanStats loanStats = loan.getLoanStats();
            if(loanStats.equals(Loan.LoanStats.대여중)) {
                loanNum ++;
            }
        }
        return loanNum >= maxLoanQuantity;
    }
    public void validMemberStatus(Member member) {
        if(member.getStatus().equals(Member.Status.DELETED)) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_DELETED);
        }
    }

    public Member deleteMember(Member member) {
        member.setStatus(Member.Status.DELETED);
        return memberRepository.save(member);
        // 논리적삭제
        // 예시 : 탈퇴후 30일간은 사용자 정보 완전삭제 X
        // 게시글도 나중에 혹시 완전 삭제 X

        // S3에 이미지 업로드 할때 게시글 삭제되면 이미지 같이 삭제시킬라고 노력했는데
        // 사실 운영하면 어디 클라우드 용량 이런거 신경 크게 안쓴데
        // 그냥 최대한 함부러 물리적삭제 하는거 조심한데
    }


}
