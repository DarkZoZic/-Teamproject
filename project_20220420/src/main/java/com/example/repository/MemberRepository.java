package com.example.repository;


import com.example.entity.entity1.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{

    // @Query(value = "SELECT MPW, MID, MADDRESS FROM MEMBERTBL WHERE MID =:username" , nativeQuery = true)
    //     public long MemberId(
    //             @Param(value="username") Long username  );
    // @Modifying
    // @Query("UPDATE MPW  set firstname = ?1 where u.lastname = ?2")
    // int setFixedFirstnameFor(String firstname, String lastname);


    // 코드가 일치하는 것 삭제
    // @Query(value = "{code : ?0}", delete = true)
    // long deleteBookCode(long code);
}
