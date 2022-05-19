package com.example.entity.entity2;

import org.springframework.beans.factory.annotation.Value;

public interface CompanyProjection {

    // 시퀀스번호
    String getMcno();

    String getMcname();

    // 닉네임
    // @Value("#{target.member.mid")
    // String getMemberMid();
    
}
