package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.entity.entity2.ClubBoard;

@Repository
public interface ClubBoardRepository extends JpaRepository<ClubBoard, Long>{
	//클럽게시판 글목록
	public List<ClubBoard> findByCbTitleContainingOrderByCbNoDesc(String cbTitle, Pageable pageable);
	
	//클럽게시판 글목록 검색기능 구현용
	public long countByCbTitleContaining(String text);
	
	// 글수정 query문
	@Query(value = "UPDATE CLUBBOARD SET CBTITLE = #{#clubboard.cbtitle}, CBCONTENT = #{#clubboard.cbcontent}, CBUPDATEDATE = CURRENT_DATE WHERE CBNO = :cbno", nativeQuery = true)
	public ClubBoard updateOne(ClubBoard clubboard, long cbno);
	
	// 이전글
	public ClubBoard findTop1ByCbNoLessThanOrderByCbNoDesc(long cbNo);
	
	
	//다음글
	public ClubBoard findTop1ByCbNoGreaterThanOrderByCbNoAsc(long cbNo);
}
