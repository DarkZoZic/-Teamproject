package com.example.repository.repository_4;

import com.example.entity.entity2.Bckeditor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BckeditorRepository extends JpaRepository<Bckeditor, Long> {
    
}
