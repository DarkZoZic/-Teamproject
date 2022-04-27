package com.example.repository;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.MemberPersonal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPSRepository 
    extends JpaRepository<MemberPersonal, String>{

        // String findBymId(String mid);
    
}
