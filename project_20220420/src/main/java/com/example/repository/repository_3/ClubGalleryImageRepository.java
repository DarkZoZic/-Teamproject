package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.entity.entity1.GImage;

@Repository
public interface ClubGalleryImageRepository extends JpaRepository<GImage, Long>{
	@Query(value = "SELECT I.* FROM GIMAGETBL I, CLUBGALLERY G WHERE G.G_NO=:gNo AND I.GI_IMGCODE=:giImgcode", nativeQuery=true)
	GImage selectImage(long gNo, long giImgcode);
	
	// 갤러리 페이지 이미지 표시
	List<GImage> findByClubgallery_gNoOrderByGiImgcodeDesc(long gNo);

	GImage findByClubgallery_gNo(long gNo);
	
	@Query(value = "SELECT GI_IMGCODE FROM GIMAGETBL WHERE G_NO=:gNo ORDER BY GI_IMGCODE DESC LIMIT :idx, 1", nativeQuery = true)
	long selectImageCode(long gNo, long idx);
	
	
}
