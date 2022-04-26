package com.example.repository.repository_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.entity.entity1.GImage;

@Repository
public interface ClubGalleryImageRepository extends JpaRepository<GImage, Long>{
	@Query(value = "SELECT I.* FROM GIMAGE I, GALLERY.G WHERE G.GNO=:gNo AND I.GIIMGCODE=:giImgcode", nativeQuery=true)
	GImage selectImage(long gNo, long giImgcode);
}
