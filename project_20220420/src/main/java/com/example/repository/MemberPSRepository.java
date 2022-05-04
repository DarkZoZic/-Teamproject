package com.example.repository;

import java.util.Optional;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.MemberPersonal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPSRepository 
    extends JpaRepository<MemberPersonal, Long>{

        // MemberPersonal findByMemberMId(String memberId);
        MemberPersonal findByMember_mId(String userid);
    
}
