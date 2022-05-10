package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.entity.entity2.ClubBoard;

@Repository
public interface ClubBoardRepository extends JpaRepository<ClubBoard, Long>{
	//클럽게시판 글목록(페이지, 검색기능)
	List<ClubBoard> findByCbtitleContainingOrderByCbnoDesc(String cbtitle, Pageable pageable);
	
	//클럽게시판 글목록 검색기능 구현용(제목에 text가 포함된 글 개수)
	long countByCbtitleContaining(String text);
	
	// 글수정 query문 //안씀
//	@Query(value = "UPDATE CLUBBOARD SET CBTITLE = #{#clubboard.cbtitle}, CBCONTENT = #{#clubboard.cbcontent}, CBUPDATEDATE = CURRENT_DATE WHERE cbno = :cbno", nativeQuery = true)
//	ClubBoard updateOne(ClubBoard clubboard, long cbno);
	
	// 이전글
	ClubBoard findTop1ByCbnoLessThanOrderByCbnoDesc(long cbno);
	
	//다음글
	ClubBoard findTop1ByCbnoGreaterThanOrderByCbnoAsc(long cbno);
}
