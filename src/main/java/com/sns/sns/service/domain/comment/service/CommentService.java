package com.sns.sns.service.domain.comment.service;


import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.board.repository.BoardRepository;
import com.sns.sns.service.domain.comment.dto.request.CommentPostRequest;
import com.sns.sns.service.domain.comment.dto.request.CommentUpdateRequest;
import com.sns.sns.service.domain.comment.dto.response.CommentGetResponse;
import com.sns.sns.service.domain.comment.dto.response.CommentPostResponse;
import com.sns.sns.service.domain.comment.dto.response.CommentUpdateResponse;
import com.sns.sns.service.domain.comment.model.CommentEntity;
import com.sns.sns.service.domain.comment.repository.CommentRepository;
import com.sns.sns.service.domain.exception.BasicException;
import com.sns.sns.service.domain.member.model.entity.Member;
import com.sns.sns.service.domain.member.repository.MemberRepository;
import com.sns.sns.service.domain.notification.dto.NotificationType;
import com.sns.sns.service.domain.notification.model.NotificationEntity;
import com.sns.sns.service.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sns.sns.service.domain.exception.ErrorCode.*;
import static com.sns.sns.service.domain.exception.ErrorCode.NOT_EXIST_COMMENT;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final NotificationRepository notificationRepository;

    public CommentPostResponse createComment(Long boardId, Member member, CommentPostRequest commentPostRequest) {
        Member findMember = memberRepository.findByUserName(member.getUsername())
                .orElseThrow(() -> new BasicException(NOT_EXIST_MEMBER, NOT_EXIST_MEMBER.message));
        BoardEntity findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new BasicException(NOT_EXIST_BOARD, NOT_EXIST_BOARD.getMessage()));

        CommentEntity newComment = new CommentEntity(commentPostRequest.content(), findBoard,findMember);
        CommentEntity savedComment = commentRepository.save(newComment);

        saveNotification(findBoard);

        return CommentPostResponse.commentPostResponse(savedComment);
    }

    public List<CommentGetResponse> getComment(Long boardId, Member member) {
        Member findMember = memberRepository.findByUserName(member.getUsername())
                .orElseThrow(() -> new BasicException(NOT_EXIST_MEMBER, NOT_EXIST_MEMBER.message));
        BoardEntity findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new BasicException(NOT_EXIST_BOARD, NOT_EXIST_BOARD.getMessage()));

        List<CommentEntity> commentEntityList = commentRepository.findAllCommentEntityByBoardEntity(findBoard);

        return commentEntityList.stream()
                        .map(comment -> CommentGetResponse.commentGetResponse(comment))
                        .toList();
    }

    public CommentUpdateResponse updateComment(Long boardId , Long commentId, Member member, CommentUpdateRequest commentUpdateRequest) {
        Member findMember = memberRepository.findByUserName(member.getUsername())
                .orElseThrow(() -> new BasicException(NOT_EXIST_MEMBER, NOT_EXIST_MEMBER.message));
        BoardEntity findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new BasicException(NOT_EXIST_BOARD, NOT_EXIST_BOARD.getMessage()));
        CommentEntity findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BasicException(NOT_EXIST_COMMENT, NOT_EXIST_COMMENT.getMessage()));

        findComment.updateComment(commentUpdateRequest.content());

        commentEditPermission(findMember,findComment);

        return CommentUpdateResponse.commentUpdateResponse(findComment);
    }

    public void deleteComment(Long boardId, Long commentId, Member member) {
        Member findMember = memberRepository.findByUserName(member.getUsername())
                .orElseThrow(() -> new BasicException(NOT_EXIST_MEMBER, NOT_EXIST_MEMBER.message));
        BoardEntity findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new BasicException(NOT_EXIST_BOARD, NOT_EXIST_BOARD.getMessage()));
        CommentEntity findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BasicException(NOT_EXIST_COMMENT, NOT_EXIST_COMMENT.getMessage()));

        commentEditPermission(findMember,findComment);

        commentRepository.delete(findComment);
    }


    public void commentEditPermission(Member member,CommentEntity comment){
        if(member.getUsername() != comment.getMember().getUsername()){
            throw new BasicException(INVALID_PERMISSION_COMMENT, INVALID_PERMISSION_COMMENT.getMessage());
        }
    }

    public void saveNotification(BoardEntity boardEntity){
        Member boardOwner = boardEntity.getMember();
        NotificationEntity notificationEntity = new NotificationEntity(boardOwner, NotificationType.COMMENT_NOTIFICATION);
        notificationRepository.save(notificationEntity);
    }

    public void deleteNotification(NotificationEntity notificationEntity){
        NotificationEntity findNotification = notificationRepository.findById(notificationEntity.getId())
                .orElseThrow(() -> new BasicException(NOT_EXIST_NOTIFICATION, NOT_EXIST_NOTIFICATION.message));
        notificationRepository.delete(notificationEntity);
    }
}