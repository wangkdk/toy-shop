package toy.shop.modules.football;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toy.shop.modules.football.form.PlayerSaveForm;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/football/player")
@Controller
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @GetMapping
    public String list(Model model, Pageable pageable) {
        // TODO : 테스트는 페이징 만들고 처리하기
        Page<Player> players = playerRepository.findAll(pageable);
        model.addAttribute("players", players);
        return "football/player-list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute(new PlayerSaveForm());
        List<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "football/player-save";
    }

    @PostMapping("/create")
    public String createSubmit(@Valid PlayerSaveForm playerSaveForm, Errors errors,
                               Model model) {
        if (errors.hasErrors()) {
            List<Team> teams = teamRepository.findAll();
            model.addAttribute("teams", teams);
            return "football/player-save";
        }

        playerService.savePlayer(playerSaveForm.getTeamId(), playerSaveForm.getName(), playerSaveForm.getNumber());
        return "redirect:/football/player";
    }
//
//    @GetMapping("/update/{id}")
//    public String update(@PathVariable Long id, Model model) {
//        // TODO : 정책이 생기면 정책따라서 권한 확인 해주어야함
//        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀 입니다."));
//        TeamModifyForm teamModifyForm = TeamModifyForm.builder()
//                .id(team.getId())
//                .league(team.getLeague())
//                .name(team.getName())
//                .build();
//
//        model.addAttribute(teamModifyForm);
//        model.addAttribute("leagues", League.values());
//
//        return "football/team-modify";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateSubmit(@PathVariable Long id, @Valid TeamModifyForm teamModifyForm, Errors errors,
//                               Model model) {
//
//        // TODO : save modify custom validator 내용이 비슷한듯 나중에 추가
//        if (errors.hasErrors()) {
//            model.addAttribute("leagues", League.values());
//            return "football/team-modify";
//        }
//        teamService.modifyTeam(id, teamModifyForm.getLeague(), teamModifyForm.getName());
//
//        return "redirect:/football/team";
//    }
//
//    @PostMapping("/delete/{id}")
//    public String deleteSubmit(@PathVariable Long id) {
//        teamService.removeTeam(id);
//        return "redirect:/football/team";
//    }
}
