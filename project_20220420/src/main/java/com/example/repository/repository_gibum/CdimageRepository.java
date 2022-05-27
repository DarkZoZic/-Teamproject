package com.example.repository.repository_gibum;

import com.example.entity.entity1.CDImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CdimageRepository  
    extends JpaRepository<CDImage, Long> {
    
}
