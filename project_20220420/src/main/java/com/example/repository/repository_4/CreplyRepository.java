package com.example.repository.repository_4;

import java.util.List;

import javax.transaction.Transactional;

import com.example.entity.entity2.CReply;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

// < 저장소에 쓰고자 하는 Entity명, Entity의 기본키 타입 >
public interface CreplyRepository extends JpaRepository<CReply, Long>{

    // 외래키, 구체적인 변수: findBy변수_하위변수
    // 외래키 가져오기: Board1_bno -> board1의 bno

    // List<BoardReplyEntity> replyList;
    // Board1 원본 글번호가 일치하는 댓글 개수
    // BoardReplyEntity -> BoardEntity
    // Board 밑에 no : Board_no (언더바 _ 로 표시함)
    // Board_no 가 하나의 변수처럼
    List<CReply> findByBoard1_bnoOrderByRenumberAsc(Long bno);

    // Qna 원본 글번호가 일치하는 댓글 
    List<CReply> findByQna_qnoOrderByRenumberAsc(long qno);

    // 내가 쓴 댓글찾기?
    List<CReply> findByMember_midOrderByRenumberDesc(String mid, Pageable pageable);
    
    // 아이디로 개수 세기
    long countByMember_mid(String mid);

    // 일괄삭제
    @Transactional
    int deleteByMember_midAndRenumberIn(String mid, Long[] renumber);
}
