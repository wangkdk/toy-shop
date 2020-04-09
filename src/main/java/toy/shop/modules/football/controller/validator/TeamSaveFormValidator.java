package toy.shop.modules.football.controller.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import toy.shop.modules.football.entity.Team;
import toy.shop.modules.football.repository.TeamRepository;
import toy.shop.modules.football.controller.form.TeamSaveForm;

@Component
@RequiredArgsConstructor
public class TeamSaveFormValidator implements Validator {

    private final TeamRepository teamRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return TeamSaveForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeamSaveForm teamSaveForm = (TeamSaveForm) target;
        Team byLeagueAndName = teamRepository.findByLeagueAndName(teamSaveForm.getLeague(), teamSaveForm.getName());
        if (byLeagueAndName != null) {
            errors.rejectValue("name", "wrong.value", "입력하신 리그의 해당 팀 이름이 이미 존재합니다.");
        }
    }
}
