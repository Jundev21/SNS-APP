package com.sns.sns.service.domain.favorite.service;


import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.board.repository.BoardRepository;
import com.sns.sns.service.domain.exception.BasicException;
import com.sns.sns.service.domain.exception.ErrorCode;
import com.sns.sns.service.domain.favorite.dto.response.AddFavoriteResponse;
import com.sns.sns.service.domain.favorite.dto.response.DeleteFavoriteResponse;
import com.sns.sns.service.domain.favorite.dto.response.GetFavoriteResponse;
import com.sns.sns.service.domain.favorite.model.FavoriteEntity;
import com.sns.sns.service.domain.favorite.repository.FavoriteRepository;
import com.sns.sns.service.domain.member.model.entity.Member;
import com.sns.sns.service.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final FavoriteRepository favoriteRepository;


    @Transactional
    public AddFavoriteResponse addFavorite(Long boardId, Member member) {

        Member findMember = memberRepository.findByUserName(member.getUsername())
                .orElseThrow(()->new BasicException(ErrorCode.NOT_EXIST_MEMBER, ErrorCode.NOT_EXIST_MEMBER.getMessage()));

        BoardEntity findBoard = boardRepository.findById(boardId)
                .orElseThrow(()->new BasicException(ErrorCode.NOT_EXIST_BOARD,ErrorCode.NOT_EXIST_BOARD.getMessage()));

        favoriteRepository
                .findFavoriteEntityByBoardEntityAndMember(findBoard, findMember)
                .ifPresent(e -> {
                    throw new BasicException(ErrorCode.ALREADY_EXIST_FAVORITE, ErrorCode.ALREADY_EXIST_FAVORITE.getMessage());
                });


        FavoriteEntity favoriteEntity = new FavoriteEntity(findBoard, findMember);
        favoriteRepository.save(favoriteEntity);

        return AddFavoriteResponse.addFavoriteResponse(favoriteEntity);
    }

    public GetFavoriteResponse getFavorite(Long boardId) {

        BoardEntity board = boardRepository.findById(boardId)
                .orElseThrow(()->new BasicException(ErrorCode.NOT_EXIST_BOARD,ErrorCode.NOT_EXIST_BOARD.getMessage()));

        FavoriteEntity favoriteEntity = favoriteRepository.findByBoardEntity(board)
                .orElseThrow(()->new BasicException(ErrorCode.NOT_EXIST_BOARD,ErrorCode.NOT_EXIST_BOARD.getMessage()));

        return GetFavoriteResponse.getFavoriteResponse(board,favoriteEntity.getMember());
    }

    public DeleteFavoriteResponse deleteFavorite(Long boardId,Member member) {
        Member findMember = memberRepository.findByUserName(member.getUsername())
                .orElseThrow(()->new BasicException(ErrorCode.NOT_EXIST_MEMBER, ErrorCode.NOT_EXIST_MEMBER.getMessage()));

        BoardEntity findBoard = boardRepository.findById(boardId)
                .orElseThrow(()->new BasicException(ErrorCode.NOT_EXIST_BOARD,ErrorCode.NOT_EXIST_BOARD.getMessage()));

        FavoriteEntity favoriteEntity = favoriteRepository
                .findFavoriteEntityByBoardEntityAndMember(findBoard, findMember)
                .orElseThrow(() -> new BasicException(ErrorCode.INVALID_PERMISSION_FAVORITE, ErrorCode.INVALID_PERMISSION_FAVORITE.getMessage()));

        favoriteRepository.delete(favoriteEntity);

        return new DeleteFavoriteResponse(findBoard.getId(), findBoard.getTitle());
    }
}
