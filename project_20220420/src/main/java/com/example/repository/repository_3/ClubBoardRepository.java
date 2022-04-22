package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.entity.entity2.ClubBoard;

@Repository
public interface ClubBoardRepository extends JpaRepository<ClubBoard, Long>{
	List<ClubBoard> findByCbTitleContainingOrderByCbNoDesc(String cbTitle, Pageable pageable);
	
	long countByCbTitleContaining(String text);
}
