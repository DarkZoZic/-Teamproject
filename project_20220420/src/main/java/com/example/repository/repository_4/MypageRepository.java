package com.example.repository.repository_4;

import com.example.entity.entity1.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MypageRepository extends JpaRepository<Member, String>  {
    
}
