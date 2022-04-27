package com.example.repository;

import com.example.entity.entity1.MemberCompany;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCPRepository 
    extends JpaRepository<MemberCompany, String>{

        // String findBymId(String mid);
    
}
