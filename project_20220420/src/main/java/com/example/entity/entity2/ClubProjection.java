package com.example.entity.entity2;


public interface ClubProjection {

    // 시퀀스번호
    Long getCno();

    String getCname();

    String getCdesc();

    String getCarea();

    String getCaddress();

    String getCimageurl();

    // 닉네임
    // @Value("#{target.member.mid")
    // String getMemberMid();
    
}
