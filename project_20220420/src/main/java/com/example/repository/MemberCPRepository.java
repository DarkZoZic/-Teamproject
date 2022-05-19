package com.example.repository;

import com.example.entity.entity1.MemberCompany;
import com.example.entity.entity2.CompanyProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCPRepository 
    extends JpaRepository<MemberCompany, String>{

        MemberCompany findByMember_Mid(String userid);

        CompanyProjection findByMcno(String cno);

    
}
