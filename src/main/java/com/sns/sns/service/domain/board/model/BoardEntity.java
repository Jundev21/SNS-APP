package com.sns.sns.service.domain.board.model;


import com.sns.sns.service.common.BaseTimeEntity;
import com.sns.sns.service.domain.board.dto.request.BoardRequest;
import com.sns.sns.service.domain.board.dto.request.BoardUpdateRequest;
import com.sns.sns.service.domain.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
