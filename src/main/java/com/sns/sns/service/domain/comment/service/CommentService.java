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
import com.sns.sns.service.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final NotificationService notificationService;
    @Transactional
    public CommentPostResponse createComment(Long boardId, Member member, CommentPostRequest commentPostRequest) {
        Member findMember = memberRepository.findByUserName(member.getUsername())
                .orElseThrow(() -> new BasicException(NOT_EXIST_MEMBER, NOT_EXIST_MEMBER.message));
        BoardEntity findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new BasicException(NOT_EXIST_BOARD, NOT_EXIST_BOARD.getMessage()));

        CommentEntity newComment = new CommentEntity(commentPostRequest.content(), findBoard,findMember);
        CommentEntity savedComment = commentRepository.save(newComment);

        NotificationEntity savedNotification = saveNotification(findBoard,findMember.getUsername());
        notificationService.sendToBrowser(findBoard.getMember(), findMember,savedNotification.getId());

        return CommentPostResponse.commentPostResponse(savedComment);
    }
    @Transactional
    public List<CommentGetResponse> getComment(Long boardId, Member member) {
        BoardEntity findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new BasicException(NOT_EXIST_BOARD, NOT_EXIST_BOARD.getMessage()));

        List<CommentEntity> commentEntityList = commentRepository.findAllCommentEntityByBoardEntity(findBoard);

        return commentEntityList.stream()
                        .map(comment -> CommentGetResponse.commentGetResponse(comment))
                        .toList();
    }
    @Transactional
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
    @Transactional
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

    @Transactional
    public void commentEditPermission(Member member,CommentEntity comment){
        if(member.getUsername() != comment.getMember().getUsername()){
            throw new BasicException(INVALID_PERMISSION_COMMENT, INVALID_PERMISSION_COMMENT.getMessage());
        }
    }
    @Transactional
    public NotificationEntity saveNotification(BoardEntity boardEntity, String sender){
        Member boardOwner = boardEntity.getMember();
        NotificationEntity notificationEntity = new NotificationEntity(boardOwner, NotificationType.COMMENT_NOTIFICATION,sender,boardEntity);
        return notificationRepository.save(notificationEntity);
    }
    @Transactional
    public void deleteNotification(NotificationEntity notificationEntity){
        NotificationEntity findNotification = notificationRepository.findById(notificationEntity.getId())
                .orElseThrow(() -> new BasicException(NOT_EXIST_NOTIFICATION, NOT_EXIST_NOTIFICATION.message));
        notificationRepository.delete(notificationEntity);
    }
}
