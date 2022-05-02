package com.example.repository.repository_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.entity2.CbImage;

@Repository
public interface ClubBoardImageRepository extends JpaRepository<CbImage, Long>{
	CbImage findByCbiImgcode(long cbiImgcode);
	
	CbImage findByClubBoard_CbNoOrderByCbiImgcodeAsc(long cbNo);
	
	void deleteByClubBoard_cbNo(long cbNo);
	
	@Query(value = "UPDATE CBIMAGE SET CBI_IMAGE=#{#cbimage.cbiImage}, CBI_IMAGENAME=#{#cbimage.cbiImagename},"
			+ " CBI_IMAGESIZE=#{#cbimage.cbiImagesize}, CBI_IMAGETYPE=#{#cbimage.cbiImagetype} WHERE CBI_IMGCODE=#{#cbimage.cbiImgcode}", 
			nativeQuery=true)
	CbImage updateOne(CbImage cbimage);
	
}
