package com.example.repository.repository_gibum;

import com.example.entity.entity2.Club;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository 
    extends JpaRepository<Club, Long>{
    
}
