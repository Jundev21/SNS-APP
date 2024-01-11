package com.sns.sns.service.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.sns.service.domain.comment.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;


    // 댓글 케이스
    // 댓글 작성
    // 댓글 수정.
    // 댓글 삭제

    // 비회원 댓글 작성
    // 비회원 댓글 수정.
    // 비회원 댓글 삭제


    @Test
    @WithMockUser
    @DisplayName("댓글 작성 성공")
    public void successToWriteComment() throws Exception {



        mockMvc.perform(post("/api/v1/board/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("댓글 조회 성공")
    public void successToGetWriteComment() throws Exception {

        mockMvc.perform(get("/api/v1/board/comment"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("댓글 수정 성공")
    public void successToUpdateComment() throws Exception {

        mockMvc.perform(get("/api/v1/board/comment/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("댓글 삭제 성공")
    public void successToDeleteComment() throws Exception {

        mockMvc.perform(get("/api/v1/board/comment/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
