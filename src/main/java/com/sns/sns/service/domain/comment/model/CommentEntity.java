package com.sns.sns.service.domain.comment.model;


import com.sns.sns.service.common.BaseTimeEntity;
import com.sns.sns.service.domain.board.model.BoardEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

@Entity
@Getter
@NoArgsConstructor
public class CommentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @OneToMany
    private BoardEntity boardEntity;
}
