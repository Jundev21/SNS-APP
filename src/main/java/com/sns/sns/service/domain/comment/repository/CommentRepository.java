package com.sns.sns.service.domain.comment.repository;

import com.sns.sns.service.domain.comment.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long>
{
}
