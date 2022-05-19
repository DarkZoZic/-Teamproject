package com.example.entity.entity1;

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
@Table(name = "CDCKEDITORTBL")
@SequenceGenerator(name = "SEQ_CDCK",
sequenceName = "SEQ_CDCKEDITOR_IMGCODE", 
allocationSize =  1,initialValue = 1)
public class CDCkeditor {

    // 이미지코드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
    generator = "SEQ_CDCK") // 시퀀스 적용
    @Column(name = "cdimgcode")
    private Long cdimgcode;

    // 이미지
    @Lob
    @Column(name = "cdimage")
    private byte[] cdimage;

    // 이미지크기
    @Column(name = "cdimagesize")
    private Long cdimagesize;

    // 이미지타입
    @Column(name = "cdimagetype")
    private String cdimagetype;

    // 이미지명
    @Column(name = "cdimagename")
    private String cdimagename;

    // 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "cdregdate")
    private Date cdregdate;

    
}
