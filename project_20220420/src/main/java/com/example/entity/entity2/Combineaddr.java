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

    
    

    // 아이디(설명)
    private Long aCode;
    // 시, 도
    private String a1;
    // 구, 군
    private String a2;
    // 주소상세
    private String aDetail;

    // 카테고리 코드
    private Long cgCode;

    // 카테고리 대분류
    private String cgCate1;

    // 카테고리 상세분류
    private String cgCate2;

    @Id
    private Long cNo;

    @Column(nullable = false)
    private String cName;

    

    


    
}
