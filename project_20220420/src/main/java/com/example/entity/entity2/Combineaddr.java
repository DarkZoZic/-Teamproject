package com.example.entity.entity2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
// view로 사용하기위한 어노테이션
@Immutable
@Table(name = "ADDRESSTABLEVIEW")
public class Combineaddr {

    

    
    @Id
    @Column(name = "CNO")
    private Long cno;
    

    
    // 주소상세
    @Column(name = "CADDRESS")
    private String caddress;

    // 카테고리 코드
    @Column(name = "CGCODE")
    private Long cgcode;

    // 카테고리 대분류
    @Column(name = "CGCATE1")
    private String cgcate1;

    // 카테고리 상세분류
    @Column(name = "CGCATE2")
    private String cgcate2;


    @Column(name = "CNAME", nullable = false)
    private String cname;

    

    


    
}
