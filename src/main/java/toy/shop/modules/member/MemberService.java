package toy.shop.modules.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username);
        if (member == null) {
            member = memberRepository.findByNickname(username);
        }

        if (member == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserMember(member);
    }

    @Transactional
    public Member saveNewMember(String email, String nickname, String password) {
        Member newMember = new Member(email, nickname, passwordEncoder.encode(password), MemberRole.ROLE_USER);
        return memberRepository.save(newMember);
    }

    public void login(Member member) {
        // 정석적인 방법은 아니지만 plain text 를 가져올 방법이 없기 때문에 이렇게 한다.
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserMember(member),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority(member.getRole().name())));
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
