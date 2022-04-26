package com.example.repository;


import com.example.entity.entity1.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{

    // @Query(value = "SELECT MPW, MID, MADDRESS FROM MEMBERTBL WHERE MID =:username" , nativeQuery = true)
    //     public long MemberId(
    //             @Param(value="username") Long username  );

}
