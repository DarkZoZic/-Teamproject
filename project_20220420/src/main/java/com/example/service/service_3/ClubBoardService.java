package com.example.service.service_3;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.entity2.ClubBoard;
import com.example.entity.entity2.CbImage;
import com.example.entity.entity2.CReply;
//import com.example.entity.entity1.Reaction;

@Service
public interface ClubBoardService {
	// 클럽게시판 글작성
    public int insertClubBoard(ClubBoard clubBoard, CbImage cbImage);
    
    // 클럽게시판 글목록
    public List<ClubBoard> selectClubBoardList(Long cbNo);
    
    // 클럽게시판 작성글 댓글목록 (댓글번호, 클럽게시판 글번호(외래키))
    public List<CReply> selectClubBoardReplyList(Long reNumber, ClubBoard clubBoard);
    
    // 클럽게시판 글 반응(좋아요, 엄지)
//    public int insertReaction(Reaction reaction);
    
    // 클럽게시판 글 반응 갯수 표시용
//    public List<Reaction> selectReactionList();
}
