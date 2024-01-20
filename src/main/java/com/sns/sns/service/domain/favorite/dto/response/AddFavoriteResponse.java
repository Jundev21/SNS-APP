package com.sns.sns.service.domain.favorite.dto.response;


import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.favorite.model.FavoriteEntity;
import lombok.Builder;

@Builder
public record AddFavoriteResponse(
        Long boardId,
        String boardTitle
) {
    public  static AddFavoriteResponse addFavoriteResponse (FavoriteEntity favoriteEntity){
        return AddFavoriteResponse.builder()
                .boardId(favoriteEntity.getBoardEntity().getId())
                .boardTitle(favoriteEntity.getBoardEntity().getTitle())
                .build();
    }
}
