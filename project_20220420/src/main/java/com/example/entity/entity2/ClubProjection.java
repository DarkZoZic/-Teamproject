package com.example.entity.entity2;

import org.springframework.beans.factory.annotation.Value;

public interface ClubProjection {

    // 시퀀스번호
    Long getCno();

    String getCname();

    String getCdesc();

    String getCarea();

    String getCaddress();

    String getCimageurl();


    // 카테고리
    @Value("#{target.category.cgcode}")
    String getCategoryCgcode();
    
    @Value("#{target.category.cgcate1}")
    String getCategoryCgcate1();
    
    @Value("#{target.category.cgcate2}")
    String getCategoryCgcate2();
    
}
