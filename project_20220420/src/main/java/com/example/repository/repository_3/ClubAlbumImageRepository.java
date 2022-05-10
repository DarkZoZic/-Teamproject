package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.entity2.CaImage;
import com.example.entity.entity2.ClubAlbum;

@Repository
public interface ClubAlbumImageRepository extends JpaRepository<CaImage, Long>{
	//gimgcode(갤러리 이미지코드, 외래키)가 일치하는 이미지 찾기. 중복방지용
	CaImage findByGimage_gimgcode(long gimgcode);
	
	//cano(앨범 번호, 외래키)가 일치하는 이미지 찾기. 중복방지용
	CaImage findByClubalbum_cano(ClubAlbum cano);
	
	// 앨범 페이지용 이미지 데이터 찾기
	List<CaImage> findByClubalbum_canoOrderByCaimgcodeAsc(long cano);
	
//	CaImage findTop1ByClubalbum_canoOrderByCaimgcodeAsc(long cano);
	
	// 앨범번호 + idx(프론트에서 반복문 돌려서 입력)로 외래키(앨범)에 해당하는 번호의 갤러리 내 이미지들의 단일 갤러리이미지코드(gimgcode)를 idx개 추출
	@Query(value = "SELECT CAIMGCODE FROM CAIMAGE WHERE CANO=:cano ORDER BY CAIMGCODE ASC LIMIT :idx, 1", nativeQuery = true)
	long selectAlbumImageCode(long cano, long idx);
}
