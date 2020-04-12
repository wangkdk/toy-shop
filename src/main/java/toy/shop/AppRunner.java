package toy.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import toy.shop.modules.football.service.TeamService;
import toy.shop.modules.member.MemberService;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final MemberService memberService;
    private final TeamService teamService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        memberService.saveNewMember("aaa@email.com", "aaa", "123");
        teamService.generateTestData();
    }
}
