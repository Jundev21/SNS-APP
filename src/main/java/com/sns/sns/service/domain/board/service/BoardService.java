package com.sns.sns.service.domain.board.service;


import com.sns.sns.service.domain.board.dto.request.BoardRequest;
import com.sns.sns.service.domain.board.dto.request.BoardUpdateRequest;
import com.sns.sns.service.domain.board.dto.response.BoardResponse;
import com.sns.sns.service.domain.board.dto.response.BoardUpdateResponse;
import com.sns.sns.service.domain.board.model.BoardEntity;
import com.sns.sns.service.domain.board.repository.BoardRepository;
import com.sns.sns.service.domain.exception.BasicException;
import com.sns.sns.service.domain.exception.ErrorCode;
import com.sns.sns.service.domain.member.model.entity.Member;
import com.sns.sns.service.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;



    @Transactional
    public BoardResponse writeBoard(BoardRequest boardRequest, Member member) {

        Member findMember = memberRepository.findByUserName(member.getUsername()).
                orElseThrow(()->new BasicException(ErrorCode.NOT_EXIST_MEMBER, "없는멤버"));

        BoardEntity board = new BoardEntity(boardRequest.title(), boardRequest.content(), member);
        BoardEntity savedBoard = boardRepository.save(board);
        return BoardResponse.boardResponse(savedBoard, member);
    }

//    public BoardResponse getBoard() {
//    }
//

    @Transactional
    public BoardUpdateResponse updateBoard(Long boardId, BoardUpdateRequest boardUpdateRequest, Member member) {

        Member findMember = memberRepository.findByUserName(member.getUsername())
                .orElseThrow(() -> new BasicException(ErrorCode.NOT_EXIST_MEMBER, ErrorCode.NOT_EXIST_MEMBER.getMessage()));

        BoardEntity board = boardRepository.findById(boardId)
                .orElseThrow(()->new BasicException(ErrorCode.NOT_EXIST_BOARD,ErrorCode.NOT_EXIST_BOARD.getMessage()));

        if(findMember != board.getMember()){
            throw new BasicException(ErrorCode.INVALID_PERMISSION,ErrorCode.INVALID_PERMISSION.getMessage());
        }

        BoardEntity newBoard = board.updateBoard(boardUpdateRequest);

        return BoardUpdateResponse.boardUpdateResponse(newBoard, findMember);

    }
//
//    public BoardResponse deleteBoard(Long boardId, Member member) {
//    }
}
