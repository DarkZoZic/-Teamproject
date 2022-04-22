package com.example.service.service_3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entity.entity2.CReply;
import com.example.entity.entity2.ClubBoard;
import com.example.repository.repository_3.CReplyRepository;
import com.example.repository.repository_3.ClubBoardRepository;

@Service
public class ClubBoardServiceImpl implements ClubBoardService{
	@Autowired
	ClubBoardRepository cbRep;
	
	@Autowired
	CReplyRepository crRep;
	
	// 클럽게시판 글쓰기
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

	// 클럽게시판 글목록
	@Override
	public List<ClubBoard> selectClubBoardList(Long cbNo, String cbTitle, Pageable pageable) {
		try 
		{
			return cbRep.findByCbTitleContainingOrderByCbNoDesc(cbTitle, pageable);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
	
	}
	
	// 클럽게시판 글 상세내용
	@Override
	public ClubBoard selectClubBoard(Long cbNo) {
		return cbRep.findById(cbNo).orElse(null);
	}

	// 클럽게시판 댓글쓰기
	@Override
	public int insertCReply(CReply cReply) {
		try {
			crRep.save(cReply);
			return 1;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public List<CReply> selectCReplylist(long cbNo) {
		try 
		{
			return crRep.findByClubBoard_CbNoOrderByReNumberDesc(cbNo);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
		
	}

	
	
	
}
