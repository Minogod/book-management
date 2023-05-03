package server.bookmanagement.member.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import server.bookmanagement.loan.history.entity.LoanHistory;
import server.bookmanagement.util.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private String phone;
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<LoanHistory> loanBooks = new ArrayList<>();
}
