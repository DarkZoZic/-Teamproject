package com.example.repository;


import java.util.List;

import javax.transaction.Transactional;

import com.example.entity.entity1.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{

    // @Query(value = "SELECT MPW, MID, MADDRESS FROM MEMBERTBL WHERE MID =:username" , nativeQuery = true)
    //     public long MemberId(
    //             @Param(value="username") Long username  );
    // @Modifying
    // @Query("UPDATE MPW  set firstname = ?1 where u.lastname = ?2")
    // int setFixedFirstnameFor(String firstname, String lastname);

    // @Transactional
    // @Modifying // select 문이 아님을 나타낸다
    // @Query(value = "UPDATE MEMBERTBL  SET M_PW = :password where M_ID = :mid", nativeQuery = true)
    // void changePwd(@Param("password")
    // String password, @Param("mid")String mid);

    Member findBymEmail(String email);


}
