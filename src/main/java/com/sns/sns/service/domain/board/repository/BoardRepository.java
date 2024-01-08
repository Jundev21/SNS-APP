package com.sns.sns.service.domain.board.repository;


import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
}
