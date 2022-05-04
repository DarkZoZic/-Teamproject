package com.example.repository.repository_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.entity1.ClubGallery;

@Repository
public interface ClubGalleryRepository extends JpaRepository<ClubGallery, Long>{
//	List<ClubGallery> findByClubAlbumContainingOrderByGNoDesc(String gName);
	
	
	@Query(value="UPDATE CLUBGALLERY SET G_NAME=#{#clubgallery.gName}, G_DESC=#{#clubgallery.gDesc} WHERE G_NO=:gNo", nativeQuery=true)
	ClubGallery updateClubGalleryByGNo(ClubGallery clubgallery, long gNo);
}
