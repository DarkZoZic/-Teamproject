package com.example.entity.entity2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Data
@Entity
// view로 사용하기위한 어노테이션
@Immutable
@Table(name = "COMBINETABLEVIEW ")
public class ComBine {

    
    // private Long vno;

    // 아이디(설명)
    @Column(name = "M_ID")
    private String mid;

    @Column(name = "M_ADDRESS")
    private String maddress;

    @Column(name = "M_NAME")
    private String mname;

    @Column(name = "M_PHONE")
    private String mphone;

    @Column(name = "C_NO")
    private Long cno;

    @Column(name = "C_AREA")
    private String carea;

    @Column(name = "C_CATE")
    private String ccate;

    @Column(name = "C_NAME")
    private String cname;

    @Column(name = "S_CONTENT")
    private String scontent;

    @Column(name = "S_CODE")
    private Long scode;

    @Id
    @Column(name = "No")
    private Long no;


    
}
