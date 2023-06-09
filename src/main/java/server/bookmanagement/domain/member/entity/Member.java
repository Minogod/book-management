package server.bookmanagement.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import server.bookmanagement.domain.loan.entity.Loan;
import server.bookmanagement.util.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseEntity {
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private boolean overDue = false;
    private LocalDateTime penaltyDeadLine;
    private boolean isDeleted = false;
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "member_loans")
    private List<Loan> loans = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


    //loan 에다가 회원의 연체패널티기간을 정해놓으면,
    //loan -> 책 한권빌릴때마다 history 식으로 남는거 ( 자꾸 많이 쌓이게됨 )
    //회원 상태 확인 ( 연체기간확인 ) -> List<Loan> 을 무조건 순회하는 로직이 반복되야함
    public enum Status{
        ACTIVE,DELETED
    }


}
