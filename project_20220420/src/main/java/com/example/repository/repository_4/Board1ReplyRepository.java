package com.example.repository.repository_4;

import java.util.List;

import com.example.entity.entity2.CReply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

// < 저장소에 쓰고자 하는 Entity명, Entity의 기본키 타입 >
public interface Board1ReplyRepository extends JpaRepository<CReply, Long>{

    // List<BoardReplyEntity> replyList;
    // 원본 글번호가 일치하는 댓글 개수
    // BoardReplyEntity -> BoardEntity
    // Board 밑에 no : Board_no (언더바 _ 로 표시함)
    // Board_no 가 하나의 변수처럼
    // List<CReply> findByBoard1_bNoOrderByNoAsc(long bno);

    
}
