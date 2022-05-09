package com.example.repository.repository_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.entity1.CsImage;

@Repository
public interface ClubScheduleImageRepository extends JpaRepository<CsImage, Long>{

}
