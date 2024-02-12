package com.sns.sns.service.integration;

import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.board.repository.BoardRepository;
import com.sns.sns.service.domain.comment.model.CommentEntity;
import com.sns.sns.service.domain.comment.repository.CommentRepository;
import com.sns.sns.service.domain.member.model.entity.Member;
import com.sns.sns.service.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class JpaTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @WithMockUser
    @DisplayName("JPA 게시판 findAll 댓글없는경우")
    public void occurNPlusOne(){

        Member member = new Member("test","1234","1234");
        memberRepository.save(member);

        for(int i=0; i<4; i++){
            BoardEntity boardEntity = new BoardEntity("test title", "test contents", member);
            boardRepository.save(boardEntity);
        }

        System.out.println("==========find Entity=========");
        List<BoardEntity> findAllBoard = boardRepository.findAll();
        assertEquals(findAllBoard.size(), 4);
    }

    @Test
    @WithMockUser
    @DisplayName("JPA 게시판 findAll N+1 발생 케이스")
    public void occurNPlusOneProblem(){

//        Member member = new Member("test","1234","1234");
//        memberRepository.save(member);

        for(int i=0; i<4; i++){
            BoardEntity boardEntity = new BoardEntity("test title", "test contents", null);
            CommentEntity commentEntity = new CommentEntity("first comment", boardEntity, null);
            commentRepository.save(commentEntity);
            boardRepository.save(boardEntity);
        }


        System.out.println("==============================find Entity=============================");
        List<BoardEntity> findAllBoard = boardRepository.findAllBoard();
    }

}
