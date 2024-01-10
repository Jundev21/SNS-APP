package com.sns.sns.service.domain.favorite.model;


import com.sns.sns.service.common.BaseTimeEntity;
import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class FavoriteEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private BoardEntity boardEntity;
    @ManyToOne
    private Member member;


    public FavoriteEntity(
            BoardEntity board,
            Member member
    ){
        this.boardEntity = board;
        this.member = member;
    }


}
