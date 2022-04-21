package com.example.repository.repository_1;

import com.example.entity.entity1.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    Member findBymId(String mid);
}
