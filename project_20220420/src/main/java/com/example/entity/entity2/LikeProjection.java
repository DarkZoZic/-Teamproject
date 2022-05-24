package com.example.entity.entity2;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

public interface LikeProjection {
    

    Long getLno();

    Date getLregdate();

    // 아이디
    @Value("#{target.member.mid}")
    String getMemberMid();

    // 아이디
    @Value("#{target.club.cno}")
    String getClubCno();



    
}
