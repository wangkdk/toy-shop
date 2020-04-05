package toy.shop.modules.football;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toy.shop.modules.football.form.TeamSaveForm;
import toy.shop.modules.football.validator.TeamSaveFormValidator;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/football/team")
@Controller
public class TeamController {

    private final TeamService teamService;
    private final TeamRepository teamRepository;
    private final TeamSaveFormValidator teamSaveFormValidator;

    @InitBinder("teamSaveForm")
    public void teamSaveFormInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(teamSaveFormValidator);
    }

    @GetMapping
    public String list(Model model) {
        // TODO : 테스트는 페이징 만들고 처리하기
        List<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "football/team-list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute(new TeamSaveForm());
        model.addAttribute("leagues", League.values());
        return "football/team-save";
    }

    @PostMapping("/create")
    public String createSubmit(@Valid TeamSaveForm teamSaveForm, Errors errors) {
        if (errors.hasErrors()) {
            return "football/team-save";
        }

        // TODO : 많아지면 Dto 를 만들어서 전달하자.
        teamService.saveTeam(teamSaveForm.getLeague(), teamSaveForm.getName());
        return "redirect:/";
    }
}
