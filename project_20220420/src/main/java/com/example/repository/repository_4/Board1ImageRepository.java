package com.example.repository.repository_4;

import com.example.entity.entity2.BImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Board1ImageRepository extends JpaRepository<BImage, Long> {
    
}
