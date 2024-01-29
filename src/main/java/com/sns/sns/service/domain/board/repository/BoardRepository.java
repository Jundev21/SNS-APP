package com.sns.sns.service.domain.board.repository;


import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.member.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    Page<BoardEntity> findAllByMember(Member member, Pageable pageable);
    Page<BoardEntity> findAllOrderByCreatedTimeDesc(Pageable pageable);
}
