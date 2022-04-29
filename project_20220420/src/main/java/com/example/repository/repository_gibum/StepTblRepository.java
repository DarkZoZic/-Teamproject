package com.example.repository.repository_gibum;

import com.example.entity.entity1.Steptbl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepTblRepository 
    extends JpaRepository<Steptbl, Long>{
    
}
