package com.example.entity.entity2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "BCKEDITORTBL")
@SequenceGenerator(name = "SEQ_BCK",
sequenceName = "SEQ_BCKEDITOR_IMGCODE", 
allocationSize =  1,initialValue = 1)
public class Bckeditor {

    // 이미지코드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
    generator = "SEQ_BCK") // 시퀀스 적용
    @Column(name = "bcimgcode")
    private Long bcimgcode;

    // 이미지
    @Lob
    @Column(name = "bcimage")
    private byte[] bcimage;

    // 이미지크기
    @Column(name = "bcimagesize")
    private Long bcimagesize;

    // 이미지타입
    @Column(name = "bcimagetype")
    private String bcimagetype;

    // 이미지명
    @Column(name = "bcimagename")
    private String bcimagename;

    // 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "bcregdate")
    private Date bcregdate;

    
}
