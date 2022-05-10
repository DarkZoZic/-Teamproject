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
@Table(name = "COMBINETABLEVIEW ")
public class ComBine {

    
    @Id
    @Column(name = "No")
    private Long no;

    // 아이디(설명)
    @Column(name = "MID")
    private String mid;

    @Column(name = "MADDRESS")
    private String maddress;

    @Column(name = "MNAME")
    private String mname;

    @Column(name = "MPHONE")
    private String mphone;

    @Column(name = "CNO")
    private Long cno;

    @Column(name = "CAREA")
    private String carea;

    @Column(name = "CCATE")
    private String ccate;

    @Column(name = "CNAME")
    private String cname;

    @Column(name = "SCONTENT")
    private String scontent;

    @Column(name = "SCODE")
    private Long scode;

    // 신청날짜
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
    @Column(name = "JCDATE")
    private Date jcdate;

    


    
}
