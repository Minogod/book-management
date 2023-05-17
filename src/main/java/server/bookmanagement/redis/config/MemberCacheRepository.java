package server.bookmanagement.redis.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import server.bookmanagement.domain.member.entity.Member;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberCacheRepository {
    //TTL 을 걸어주는것이 좋음

    private final RedisTemplate<String, Member> memberRedisTemplate;
    private final static Duration MEMBER_CACHE_TTL = Duration.ofDays(1);

    //저장
    public void setMember(Member member) {
        String key = getKey(member.getEmail());
        log.info("Set Member to Redis {}:{}", key, member);
        memberRedisTemplate.opsForValue().set(key, member, MEMBER_CACHE_TTL);
    }
    //불러오기
    public Optional<Member> getMember(String email){
        String key = getKey(email);
        Member member = memberRedisTemplate.opsForValue().get(key);
        log.info("Get Member to Redis {}:{}", key, member);
        return Optional.ofNullable(member);
    }
    private String getKey(String email) {
        return "MEMBER:" + email;
    }
}
