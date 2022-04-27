package com.example.service.service_3;

import org.springframework.stereotype.Service;

import com.example.entity.entity2.CbImage;

@Service
public interface ClubBoardImageService {
	// 클럽게시판 글쓰기(이미지넣기)
	public int insertClubBoardImage(CbImage cbImage);
	
	// 클럽게시판 글상세내용(이미지)
	public CbImage selectClubBoardImage(long cbNo);
	
	 // 클럽게시판 글수정(이미지)
    public int updateClubBoardImage(CbImage cbImage);
    
    // 클럽게시판 글 삭제시 해당 글에 올렸던 이미지파일 삭제하기
//    public int deleteClubBoardImage(CbImage cbImage);
}
