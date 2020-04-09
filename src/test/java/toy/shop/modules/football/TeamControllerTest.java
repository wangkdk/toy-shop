package toy.shop.modules.football;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import toy.shop.modules.football.entity.League;
import toy.shop.modules.football.entity.Team;
import toy.shop.modules.football.repository.TeamRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@Transactional
@SpringBootTest
class TeamControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TeamRepository teamRepository;

    @BeforeEach
    void beforeEach() {
        Team team = Team.builder()
                .league(League.EPL)
                .name("리버풀")
                .build();
        teamRepository.save(team);
    }

    @DisplayName("팀 등록 화면")
    @WithMockUser
    @Test
    void saveTeamView() throws Exception {
        mockMvc.perform(get("/football/team/create")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("football/team-save"))
                .andExpect(model().attributeExists("teamSaveForm"))
                .andExpect(model().attributeExists("leagues"));
    }

    @DisplayName("팀 등록 성공")
    @WithMockUser
    @Test
    void saveTeam() throws Exception {
        mockMvc.perform(post("/football/team/create")
                .param("league", String.valueOf(League.EPL))
                .param("name","첼시")
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection());

        Team team = teamRepository.findByLeagueAndName(League.EPL, "첼시");

        assertNotNull(team);
    }

    @DisplayName("같은리그에 같은이름의 팀 존재할 수 없음")
    @WithMockUser
    @Test
    void saveTeam_exists_league_team() throws Exception {
        mockMvc.perform(post("/football/team/create")
                .param("league", String.valueOf(League.EPL))
                .param("name","리버풀")
                .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @DisplayName("팀 수정 화면")
    @WithMockUser
    @Test
    void modifyTeamView() throws Exception {
        mockMvc.perform(get("/football/team/update/1")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("football/team-modify"))
                .andExpect(model().attributeExists("teamModifyForm"))
                .andExpect(model().attributeExists("leagues"));
    }

    @DisplayName("팀 수정 성공")
    @WithMockUser
    @Test
    void modifyTeam() throws Exception {
        mockMvc.perform(post("/football/team/update/1")
                .param("league", String.valueOf(League.EPL))
                .param("name","첼시")
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection());

        Team team = teamRepository.findById(1L).get();
        assertEquals("첼시", team.getName());
    }

    @DisplayName("팀 삭제 성공")
    @WithMockUser
    @Test
    void removeTeam() throws Exception {
        mockMvc.perform(post("/football/team/delete/1")
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection());

        assertThrows(IllegalArgumentException.class, () -> teamRepository.findById(1L).orElseThrow(()->new IllegalArgumentException("팀이 존재하지 않습니다.")));
    }
}