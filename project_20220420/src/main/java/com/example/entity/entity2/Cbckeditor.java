package com.example.entity.entity2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name="CBCKEDITOR")
@SequenceGenerator(name = "SEQ_CBCK", sequenceName = "SEQ_CBCKIMG_CODE", allocationSize = 1, initialValue = 1)
public class Cbckeditor {
	// ckeditor 이미지 미리보기용 이미지 임시저장소
	
	// 이미지번호 // 기본키
	@Id
	@GeneratedValue(generator = "SEQ_CBCK", strategy = GenerationType.SEQUENCE)
	@Column(name="CBCKIMGCODE")
	private Long cbckimgcode;
	
	// 이미지파일 데이터
	@Column(name="CBCKIMAGE")
	private byte[] cbckimage;
	
	// 이미지파일 크기
	@Column(name="CBCKIMAGESIZE")
	private Long cbckimagesize;
	
	// 이미지파일 이름
	@Column(name="CBCKIMAGENAME")
	private String cbckimagename;
	
	// 이미지파일 유형
	@Column(name="CBCKIMAGETYPE")
	private String cbckimagetype;
	
	// 이미지파일 등록일
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name="CBCKIMGREGDATE")
	private Date cbckimgregdate;
}
