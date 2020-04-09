package toy.shop.modules.football.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import toy.shop.modules.football.repository.TeamRepository;
import toy.shop.modules.football.service.TeamService;
import toy.shop.modules.football.controller.form.TeamModifyForm;
import toy.shop.modules.football.controller.form.TeamSaveForm;
import toy.shop.modules.football.controller.validator.TeamSaveFormValidator;
import toy.shop.modules.football.entity.League;
import toy.shop.modules.football.entity.Team;

import javax.validation.Valid;

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
    public String list(Model model, Pageable pageable) {
        // TODO : 테스트는 페이징 만들고 처리하기
        Page<Team> teams = teamRepository.findAll(pageable);
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
    public String createSubmit(@Valid TeamSaveForm teamSaveForm, Errors errors,
                               Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("leagues", League.values());
            return "football/team-save";
        }

        // TODO : 많아지면 Dto 를 만들어서 전달하자.
        teamService.saveTeam(teamSaveForm.getLeague(), teamSaveForm.getName());
        return "redirect:/football/team";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        // TODO : 정책이 생기면 정책따라서 권한 확인 해주어야함
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀 입니다."));
        TeamModifyForm teamModifyForm = TeamModifyForm.builder()
                .id(team.getId())
                .league(team.getLeague())
                .name(team.getName())
                .build();

        model.addAttribute(teamModifyForm);
        model.addAttribute("leagues", League.values());

        return "football/team-modify";
    }

    @PostMapping("/update/{id}")
    public String updateSubmit(@PathVariable Long id, @Valid TeamModifyForm teamModifyForm, Errors errors,
                               Model model) {

        // TODO : save modify custom validator 내용이 비슷한듯 나중에 추가
        if (errors.hasErrors()) {
            model.addAttribute("leagues", League.values());
            return "football/team-modify";
        }
        teamService.modifyTeam(id, teamModifyForm.getLeague(), teamModifyForm.getName());

        return "redirect:/football/team";
    }

    @PostMapping("/delete/{id}")
    public String deleteSubmit(@PathVariable Long id) {
        teamService.removeTeam(id);
        return "redirect:/football/team";
    }

    @GetMapping("/data")
    public String generateTestData() {
        teamService.generateTestData();
        return "redirect:/";
    }
}