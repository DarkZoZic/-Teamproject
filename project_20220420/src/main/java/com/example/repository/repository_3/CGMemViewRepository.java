package com.example.repository.repository_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.entity1.CGMemView;

@Repository
public interface CGMemViewRepository extends JpaRepository<CGMemView, Long>{
	
}
