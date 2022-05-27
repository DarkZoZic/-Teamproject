package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.entity2.Club;
import com.example.entity.entity2.ClubBoard;

@Repository
public interface ClubBoardRepository extends JpaRepository<ClubBoard, Long>{
	
	//클럽게시판 글목록(페이지, 제목기준 검색기능)
	List<ClubBoard> findByCbtitleContainingAndClub_cnoOrderByCbnoDesc(String cbtitle, long cno, Pageable pageable);
	
	long countByCbtitleContainingAndClub_cno(String cbtitle, long cno);
	
	//클럽게시판 글목록(페이지, 내용기준 검색기능)
	List<ClubBoard> findByCbcontentContainingAndClub_cnoOrderByCbnoDesc(String cbcontent, long cno, Pageable pageable);
	
	long countByCbcontentContainingAndClub_cno(String cbcontent, long cno);
	
	//클럽게시판 글목록(페이지, 작성자명기준 검색기능)
	List<ClubBoard> findByMember_midContainingAndClub_cnoOrderByCbnoDesc(String mid, long cno, Pageable pageable);
	
	long countByMember_midContainingAndClub_cno(String mid, long cno);
	
	//클럽게시판 글목록(페이지, 모든기준 검색기능) // 미완성
	@Query(value="SELECT * FROM CLUBBOARDTBL C WHERE C.CBTITLE LIKE '%:text%' OR C.CBCONTENT LIKE '%:text%' OR C.MID LIKE '%:text%' AND C.CNO=:cno ORDER BY CBNO DESC", nativeQuery = true)
	List<ClubBoard> findByAllOptions(String text, long cno, Pageable pageable);
	
	List<ClubBoard> findByClub_cnoOrderByCbnoDesc(long cno, Pageable pageable);
	
	long countByClub_cno(long cno);
	
//	List<ClubBoard> findByCbtitleAndCbcontentAndMember_mnameContainingOrderByCbnoDesc(String text, Pageable pageable);
	
	// 글수정 query문 //안씀
//	@Query(value = "UPDATE CLUBBOARD SET CBTITLE = #{#clubboard.cbtitle}, CBCONTENT = #{#clubboard.cbcontent}, CBUPDATEDATE = CURRENT_DATE WHERE cbno = :cbno", nativeQuery = true)
//	ClubBoard updateOne(ClubBoard clubboard, long cbno);
	
	// 이전글
	ClubBoard findTop1ByClub_cnoAndCbnoLessThanOrderByCbnoDesc(long cno, long cbno);
	
	//다음글
	ClubBoard findTop1ByClub_cnoAndCbnoGreaterThanOrderByCbnoAsc(long cno, long cbno);
	
	// 클럽게시판 글 상세화면 데이터
	ClubBoard findByCbnoAndClub_cno(long cbno, long cno);
}
