package com.example.entity.entity2;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "CBIMAGETBL")
@SequenceGenerator(name = "SEQ_CBIMAGE",
sequenceName = "SEQ_CBIMAGE_IMGCODE", 
allocationSize = 1, initialValue = 1)
public class CbImage {
  // 이미지코드
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_CBIMAGE") // 시퀀스 적용
  private Long cbiImgcode;
  // 등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "CBIREGDATE")
  private Date cbiRegdate;
  // 이미지
  @Lob
  private byte[] cbiImage;
  // 이미지크기
  private Long cbiImagesize;
  // 이미지타입
  private String cbiImagetype;
  // 이미지이름
  private String cbiImagename;
  // 클럽게시판
  @ManyToOne
  @JoinColumn(name = "b_no")
  private ClubBoard clubBoard;
}