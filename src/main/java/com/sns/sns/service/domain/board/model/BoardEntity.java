package com.sns.sns.service.domain.board.model;


import com.sns.sns.service.common.BaseTimeEntity;
import com.sns.sns.service.domain.board.dto.request.BoardRequest;
import com.sns.sns.service.domain.board.dto.request.BoardUpdateRequest;
import com.sns.sns.service.domain.favorite.model.FavoriteEntity;
import com.sns.sns.service.domain.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class BoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String contents;
    @ManyToOne
    private Member member;
    @OneToMany(mappedBy = "boardEntity")
    private List<FavoriteEntity> favoriteEntityList = new ArrayList<>();

    //좋아요는 순서 상관없이 추가 삭제가 유용함으로 이부분 map 으로 리팩토링 해보기

    public BoardEntity(
            String title,
            String contents,
            Member member
    ){
        this.title = title;
        this.contents = contents;
        this.member = member;
    }

    public BoardEntity updateBoard(BoardUpdateRequest boardUpdateRequest){
        this.title = boardUpdateRequest.title();
        this.contents = boardUpdateRequest.content();
        return this;
    }


}
