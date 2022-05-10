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
@Table(name = "MEMBERNICKNAMEVIEW ")
public class MemberNicknameview {

    
    @Id
    @Column(name = "MPNO")
    private Long mpno;

    // 아이디
    @Column(name = "MID")
    private String mid;
    
    @Column(name = "MPNICKNAME")
    private String mpnickname;

    @Column(name = "CNO")
    private Long cno;

    @Column(name = "SCODE")
    private Long scode;

    

    

    


    
}
