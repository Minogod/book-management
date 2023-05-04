package server.bookmanagement.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.bookmanagement.member.entity.Member;
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}
