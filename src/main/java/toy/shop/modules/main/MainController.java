package toy.shop.modules.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import toy.shop.modules.member.CurrentMember;
import toy.shop.modules.member.Member;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String home(@CurrentMember Member member, Model model) {
        if (member != null) {
            model.addAttribute(member);
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
