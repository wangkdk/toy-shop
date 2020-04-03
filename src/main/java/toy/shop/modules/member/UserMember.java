package toy.shop.modules.member;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserMember extends User {

    private Member member;

    public UserMember(Member member) {
        super(member.getNickname(), member.getPassword(), List.of(new SimpleGrantedAuthority(member.getRole().getValue())));
        this.member = member;
    }
}
