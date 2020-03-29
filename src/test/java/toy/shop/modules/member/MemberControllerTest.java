package toy.shop.modules.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("회원 가입 화면 테스트")
    @Test
    void signUpForm() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("member/sign-up"))
                .andExpect(model().attributeExists("signUpForm"))
                .andExpect(unauthenticated());
    }

    @DisplayName("회원 가입 테스트")
    @Test
    void signUpSubmit() throws Exception {
        mockMvc.perform(post("/sign-up")
                .param("email","aaa@email.com")
                .param("nickname","aaa")
                .param("password","123456789")
                .with(csrf())
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(authenticated());

        Member member = memberRepository.findByEmail("aaa@email.com");
        assertNotNull(member);
        assertNotEquals(member.getPassword(), "123456789");
    }

    @DisplayName("회원 가입 테스트 - 입력값 오류")
    @Test
    void signUpSubmit_input_wrong() throws Exception {
        mockMvc.perform(post("/sign-up")
                .param("email","aaa")
                .param("nickname","aaa")
                .param("password","12345")
                .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("member/sign-up"))
                .andExpect(unauthenticated());
    }


}