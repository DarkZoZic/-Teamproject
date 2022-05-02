package com.example.repository.repository_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.entity.entity1.GImage;

@Repository
public interface ClubGalleryImageRepository extends JpaRepository<GImage, Long>{
	@Query(value = "SELECT I.* FROM GIMAGETBL I, CLUBGALLERY G WHERE G.G_NO=:gNo AND I.GI_IMGCODE=:giImgcode", nativeQuery=true)
	GImage selectImage(long gNo, long giImgcode);
}
