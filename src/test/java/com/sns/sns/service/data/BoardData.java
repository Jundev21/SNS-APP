package com.sns.sns.service.data;

import com.sns.sns.service.domain.board.dto.request.BoardRequest;
import com.sns.sns.service.domain.board.dto.request.BoardUpdateRequest;
import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.member.model.entity.Member;

public class BoardData {

    public static BoardEntity newBoard(BoardUpdateRequest boardUpdateRequest, Member member){

        return new BoardEntity(boardUpdateRequest.title(), boardUpdateRequest.content(), member);

    }
}
