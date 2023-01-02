package com.example.interfaces.member;

import com.example.domain.member.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.TestData.ABNORMAL_MEMBER_ID;
import static com.example.TestData.NORMAL_MEMBER_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberApiController.class)
public class MemberApiControllerTest {

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberDtoMapper memberDtoMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("정상 케이스 테스트")
    void testRegisterMemberV2() throws Exception {
        MemberDto.CreateV2 createV2 = MemberDto.CreateV2.builder()
                .id(NORMAL_MEMBER_ID)
                .name("testname")
                .build();

        given(memberService.registerMember(any())).willReturn(NORMAL_MEMBER_ID);

        mockMvc.perform(post("/api/v2/members")
                        .content(objectMapper.writeValueAsString(createV2))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(NORMAL_MEMBER_ID))
                ;
    }

    @Test
    @DisplayName("비정상 케이스 테스트")
    void testRegisterMemberV2_01() throws Exception {
        MemberDto.CreateV2 createV2 = MemberDto.CreateV2.builder()
                .id(ABNORMAL_MEMBER_ID)
                .name("testname")
                .build();

        given(memberService.registerMember(any())).willReturn(ABNORMAL_MEMBER_ID);

        mockMvc.perform(post("/api/v2/members")
                        .content(objectMapper.writeValueAsString(createV2))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError());
    }

}
