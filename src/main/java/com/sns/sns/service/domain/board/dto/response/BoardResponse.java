package com.sns.sns.service.domain.board.dto.response;

import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.member.dto.response.BasicUserInfoResponse;
import com.sns.sns.service.domain.member.model.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
public record BoardResponse (
        Long id,
        String title,
        String content,
        BasicUserInfoResponse member,
        LocalDateTime createdTime

){

    public static BoardResponse boardResponse(BoardEntity board, Member member){

        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContents())
                .member(BasicUserInfoResponse.basicUserInfoResponse(member))
                .createdTime(board.getCreatedTime())
                .build();
    }
}
