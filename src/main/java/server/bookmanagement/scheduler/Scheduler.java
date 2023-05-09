package server.bookmanagement.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import server.bookmanagement.domain.member.entity.Member;
import server.bookmanagement.domain.member.repository.MemberRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class Scheduler {

    private final MemberRepository memberRepository;

    //Todo: 매일 12시 정각에 Member 전체 한바퀴 돌면서, 패널티기간끝나는지 확인후 상태변경 로직
    // penaltyDeadLine 확인
    // 이거끝나면 penaltyDeadLine 초기화 및 overDue false 로 변경

    @Scheduled(cron = "0 0 0 * * *") // 매일 오전 12시 정각에 실행
    public void setMemberPenalty() {
        log.info("스케줄러 실행됨");
        List<Member> members = memberRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime test = LocalDateTime.of(2023, 5, 25, 12, 0, 0);
        LocalDate localDate = now.toLocalDate();
        for(Member member : members) {
            if(member.isOverDue()) {
                LocalDateTime penaltyDeadLine = member.getPenaltyDeadLine();
                LocalDate localDate1 = penaltyDeadLine.toLocalDate();
                if(localDate.equals(localDate1)) {
                    member.setOverDue(false);
                    member.setPenaltyDeadLine(null);
                    log.info("패널티 없어짐");
                }
            }
        }
        memberRepository.saveAll(members);
        log.info("스케줄러 돌고나서 저장완료");
        // 실행할 작업 내용
    }
}
