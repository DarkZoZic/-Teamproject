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
		try 
		{
			cbRep.save(clubBoard);
			return 1;
		} 
		catch (Exception e) 
		{
			return 0;
		}
	}

	// 클럽게시판 글목록
	@Override
	public List<ClubBoard> selectClubBoardList(Long cbNo, String cbTitle, Pageable pageable) {
		try 
		{
			return cbRep.findByCbtitleContainingOrderByCbnoDesc(cbTitle, pageable);
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	
	// 클럽게시판 글 상세내용
	@Override
	public ClubBoard selectClubBoard(Long cbNo) {
		return cbRep.findById(cbNo).orElse(null);
	}
	
	// 클럽게시판 글수정
	@Override
	public int updateClubBoard(ClubBoard clubBoard) {
		try 
		{
//			cbRep.updateOne(clubBoard, clubBoard.getCbno());
			return 1;
		} 
		catch (Exception e) 
		{
			return 0;
		}
	}
	
	// 클럽게시판 글삭제
	@Override
	public int deleteClubBoard(Long cbNo) {
		try {
			cbRep.deleteById(cbNo);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	// 클럽게시판 댓글쓰기
	@Override
	public int insertCReply(CReply cReply) {
		try {
			crRep.save(cReply);
			return 1;
		} 
		catch (Exception e) {
			return 0;
		}
		
	}

	//클럽게시판 댓글목록
	@Override
	public List<CReply> selectCReplylist(long cbNo) {
		try 
		{
			return crRep.findByClubboard_CbnoOrderByRenumberDesc(cbNo);
		} 
		catch (Exception e) 
		{
			return null;
		}
	}

	

	
	
	
}
