package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.entity1.ClubGallery;

@Repository
public interface ClubGalleryRepository extends JpaRepository<ClubGallery, Long>{
//	public List<ClubGallery> findByClubAlbumContainingOrderByGNoDesc(String gName);
}
