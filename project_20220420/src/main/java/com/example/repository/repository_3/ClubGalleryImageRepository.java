package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.entity.entity1.GImage;

@Repository
public interface ClubGalleryImageRepository extends JpaRepository<GImage, Long>{
	@Query(value = "SELECT I.* FROM GIMAGE I, CLUBGALLERY G WHERE G.CGNO=:cgno AND I.GIMGCODE=:gimgcode", nativeQuery=true)
	GImage selectImage(long cgNo, long gimgcode);
	
	// 갤러리 페이지용 이미지 데이터 찾기
	List<GImage> findByClubgallery_cgnoOrderByGimgcodeAsc(long cgno);
	
	// 앨범 페이지용 이미지 데이터 찾기
	List<GImage> findByClubalbum_canoOrderByGimgcodeAsc(long cano);

	// 갤러리 번호(외래키) 일치하는 이미지 찾기
	GImage findByClubgallery_cgNo(long cgno);
	
	// 외래키(cgNo = 갤러리번호) + idx(프론트에서 반복문 돌려서 입력)로 외래키(갤러리)에 해당하는 번호의 갤러리 내 이미지들의 단일 갤러리이미지코드(gimgcode)를 idx개 추출
	@Query(value = "SELECT GIMGCODE FROM GIMAGE WHERE CGNO=:cgno ORDER BY GIMGCODE ASC LIMIT :idx, 1", nativeQuery = true)
	long selectImageCode(long cgno, long idx);
	
	// 외래키(caNo = 앨범번호) + idx(프론트에서 반복문 돌려서 입력)로 외래키(앨범)에 해당하는 번호의 갤러리 내 이미지들의 단일 갤러리이미지코드(gimgcode)를 idx개 추출
	@Query(value = "SELECT GIMGCODE FROM GIMAGE WHERE CANO=:cano ORDER BY GIMGCODE ASC LIMIT :idx, 1", nativeQuery = true)
	long selectAlbumImageCode(long cano, long idx);
	
	// 갤러리 삭제 시 갤러리에 등록된 이미지 데이터 일괄삭제
	void deleteByClubgallery_cgNo(long cgno);
	
	// 앨범에 갤러리 이미지 추가
	@Query(value = "UPDATE GIMAGE SET CANO=:cano WHERE GIMGCODE=:gimgcode", nativeQuery = true)
	void insertImageInAlbum(long cano, long gimgcode);
}
