package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.entity.entity2.ClubBoard;

@Repository
public interface ClubBoardRepository extends JpaRepository<ClubBoard, Long>{
	//클럽게시판 글목록
	List<ClubBoard> findByCbTitleContainingOrderByCbNoDesc(String cbTitle, Pageable pageable);
	
	//클럽게시판 글목록 검색기능 구현용
	long countByCbTitleContaining(String text);
}
