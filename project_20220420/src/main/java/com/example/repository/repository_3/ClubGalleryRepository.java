package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.entity1.ClubGallery;

@Repository
public interface ClubGalleryRepository extends JpaRepository<ClubGallery, Long>{
	// 갤러리목록 (제목기준 검색, 페이지 기능 포함)
	List<ClubGallery> findByCgnameContainingAndClub_cnoOrderByCgnoDesc(String cgname, long cno, Pageable pageable);
	
	List<ClubGallery> findByCgdescContainingAndClub_cnoOrderByCgnoDesc(String cgdesc, long cno, Pageable pageable);
	
	List<ClubGallery> findByMember_midContainingAndClub_cnoOrderByCgnoDesc(String mid, long cno, Pageable pageable);
	
	@Query(value="SELECT * FROM CLUBGALLERY C, Member M WHERE C.CGNAME LIKE '%:text%' OR C.CGDESC LIKE '%:text%' OR M.MID LIKE '%:text%' ORDER BY CGNO DESC", nativeQuery = true)
	List<ClubGallery> findByAllOptions(String text, Pageable pageable);
	
	List<ClubGallery> findByClub_cnoOrderByCgnoDesc(long cno, Pageable pageable);
	
	// 갤러리 상세내용 화면 데이터
	ClubGallery findByCgnoAndClub_cno(long Cgno, long cno);
}
