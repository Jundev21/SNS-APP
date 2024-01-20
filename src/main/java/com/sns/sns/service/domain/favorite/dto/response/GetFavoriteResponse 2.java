package com.sns.sns.service.domain.favorite.dto.response;

import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.member.dto.response.BasicUserInfoResponse;
import com.sns.sns.service.domain.member.model.entity.Member;
import lombok.Builder;


@Builder
public record GetFavoriteResponse(
        int favoriteNumber,
        BasicUserInfoResponse basicUserInfoResponse

) {

    public  static  GetFavoriteResponse getFavoriteResponse(BoardEntity board, Member member){
        return GetFavoriteResponse.builder()
                .favoriteNumber(board.getFavoriteEntityList().size())
                .basicUserInfoResponse(BasicUserInfoResponse.basicUserInfoResponse(member))
                .build();
    }

}
