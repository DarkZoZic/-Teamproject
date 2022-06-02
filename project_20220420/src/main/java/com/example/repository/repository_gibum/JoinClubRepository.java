package com.example.repository.repository_gibum;

import java.util.List;

import com.example.entity.entity1.JoinClub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinClubRepository     
    extends JpaRepository<JoinClub, Long>{

        
    
}
