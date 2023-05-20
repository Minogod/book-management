package server.bookmanagement.security.auths.userdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import server.bookmanagement.domain.member.entity.Member;
import server.bookmanagement.domain.member.service.MemberService;
import server.bookmanagement.security.auths.utils.CustomAuthorityUtils;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberService memberService;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member memberByEmail = memberService.findMemberByEmail(username);
//        memberCacheRepository.setMember(memberByEmail);

        return new MemberDetails(memberByEmail);
    }

    public final class MemberDetails extends Member implements UserDetails {
        MemberDetails(Member member){
            setId(member.getId());
            setName(member.getName());
            setEmail(member.getEmail());
            setPassword(member.getPassword());
            setPhone(member.getPhone());
            setRoles(member.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities(){
            return authorityUtils.createdAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }

//    public void updateMemberPassword(MemberDetails member) {
//        memberRepository.updateMemberPassword(member.getMemberId(),member.getPassword());
//    }

}
