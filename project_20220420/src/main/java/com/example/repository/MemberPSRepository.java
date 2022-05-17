package com.example.repository;

import java.util.Optional;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.MemberPersonal;
import com.example.entity.entity2.MemberNicknameview;
import com.example.entity.entity2.MemberProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPSRepository 
    extends JpaRepository<MemberPersonal, Long>{

        MemberPersonal findByMember_Mid(String userid);

        MemberProjection findByMpno(long id);

        // MemberNicknameview findByMpno(long id);



        // MemberPersonal findByMember_MId(String userid);
    
}
