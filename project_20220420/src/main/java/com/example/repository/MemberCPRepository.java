package com.example.repository;

import com.example.entity.entity1.MemberCompany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCPRepository 
    extends JpaRepository<MemberCompany, String>{

        // String findBymId(String mid);
    
}
