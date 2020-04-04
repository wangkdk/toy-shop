package toy.shop.modules.football;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import toy.shop.modules.member.MemberRole;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Transactional
@SpringBootTest
class TeamControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TeamRepository teamRepository;

    @DisplayName("팀 등록 성공")
    @WithMockUser
    @Test
    void saveTeam() throws Exception {
        mockMvc.perform(post("/football/team/create")
                .param("league", String.valueOf(League.EPL))
                .param("name","리버풀")
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection());

        Team team = teamRepository.findByLeagueAndName(League.EPL, "리버풀");

        assertNotNull(team);
    }

}