package com.example.repository.repository_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.entity1.GImage;
import com.example.entity.entity2.ClubAlbum;

@Repository
public interface ClubAlbumRepository extends JpaRepository<ClubAlbum, Long>{
	
}