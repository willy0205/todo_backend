package hello.toy.todoapp.member.service;

import hello.toy.todoapp.member.domain.Member;
import hello.toy.todoapp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        return memberRepository.findMemberByLoginId(loginId)
            .map(this::createUser)
            .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));
    }

    private org.springframework.security.core.userdetails.User createUser(Member member) {
        String authorityName = member.getAuthority().getAuthorityName().name();
        List<GrantedAuthority> grantedAuthority = List.of(new SimpleGrantedAuthority(authorityName));
        return new org.springframework.security.core.userdetails.User(member.getLoginId(),
            member.getPassword(),
            grantedAuthority);
    }
}
