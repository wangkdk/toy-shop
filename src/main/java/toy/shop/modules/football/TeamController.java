package toy.shop.modules.football;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/football/team")
@Controller
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute(new TeamSaveForm());
        return "football/team-save";
    }

    @PostMapping("/create")
    public String createSubmit(@Valid TeamSaveForm teamSaveForm, Errors errors) {
        // TODO : unique 제약조건 valid 필요할듯..?
        if (errors.hasErrors()) {
            return "football/team-save";
        }

        // TODO : 많아지면 Dto 를 만들어서 전달하자.
        teamService.saveTeam(teamSaveForm.getLeague(), teamSaveForm.getName());
        return "redirect:/";
    }
}
