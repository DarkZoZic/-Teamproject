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
@Table(name = "QCKEDITORTBL")
@SequenceGenerator(name = "SEQ_QCK",
sequenceName = "SEQ_QCKEDITOR_IMGCODE", 
allocationSize =  1,initialValue = 1)
public class Qckeditor {

    // 이미지코드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
    generator = "SEQ_QCK") // 시퀀스 적용
    @Column(name = "qcimgcode")
    private Long qcimgcode;

    // 이미지
    @Lob
    @Column(name = "qcimage")
    private byte[] qcimage;

    // 이미지크기
    @Column(name = "qcimagesize")
    private Long qcimagesize;

    // 이미지타입
    @Column(name = "qcimagetype")
    private String qcimagetype;

    // 이미지명
    @Column(name = "qcimagename")
    private String qcimagename;

    // 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "qcregdate")
    private Date qcregdate;

    
}
