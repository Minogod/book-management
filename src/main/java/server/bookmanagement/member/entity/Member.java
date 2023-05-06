package server.bookmanagement.member.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import server.bookmanagement.loan.entity.Loan;
import server.bookmanagement.util.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private String phone;
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Loan> loanBooks = new ArrayList<>();
}
