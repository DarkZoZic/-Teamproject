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
	
	// 외래키(gNo = 갤러리번호) + idx(프론트에서 반복문 돌려서 입력)로 외래키에 해당하는 번호의 갤러리 내 이미지들의 단일 갤러리이미지코드(giImgcode)를 idx개 추출
	@Query(value = "SELECT GI_IMGCODE FROM GIMAGETBL WHERE G_NO=:gNo ORDER BY GI_IMGCODE DESC LIMIT :idx, 1", nativeQuery = true)
	long selectImageCode(long gNo, long idx);
	
	
}
