package com.example.service.service_3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.entity2.CReply;
import com.example.entity.entity2.ClubBoard;
import com.example.repository.repository_3.ClubBoardRepository;

public class ClubBoardServiceImpl implements ClubBoardService{
	@Autowired
	ClubBoardRepository cbRep;
	
	//클럽게시판 글쓰기
	@Override
	public int insertClubBoard(ClubBoard clubBoard) {
		try {
			cbRep.save(clubBoard);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	//클럽게시판 글목록
	@Override
	public List<ClubBoard> selectClubBoardList(Long cbNo) {
		
		return null;
	}

	@Override
	public List<CReply> selectClubBoardReplyList(Long reNumber, ClubBoard clubBoard) {
		return null;
	}

}
