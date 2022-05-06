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
	List<ClubGallery> findByCgNameContainingOrderByCgNoDesc(String gName, Pageable pageable);
	
	// 갤러리 검색기능 구현용(제목에 text가 포함된 갤러리 개수)
	long countByCgNameContaining(String text);
	
	// 갤러리 제목, 설명 수정(안씀)
	@Query(value="UPDATE CLUBGALLERY SET G_NAME=#{#clubgallery.cgName}, G_DESC=#{#clubgallery.cgDesc} WHERE G_NO=:cgNo", nativeQuery=true)
	ClubGallery updateClubGalleryByCgNo(ClubGallery clubgallery, long cgNo);
}
