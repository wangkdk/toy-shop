package toy.shop.modules.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String username);

    Member findByNickname(String username);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
