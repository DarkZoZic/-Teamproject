package com.example.repository.repository_4;

import com.example.entity.entity1.Like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long>{
    
}
