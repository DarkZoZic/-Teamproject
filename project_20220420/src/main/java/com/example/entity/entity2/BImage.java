package com.example.entity.entity2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class BImage {
  // 게시판이미지코드
  @Id
  private Long biImgcode;
  // 게시판이미지등록일

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "BIREGDATE")
  private Date biRegdate;
  // 게시판이미지
  @Lob
  private byte[] biImage;
  // 게시판이미지크기
  private Long biImagesize;
  // 게시판이미지타입
  private String biImagetype;
  // 게시판이미지명
  private String biImagename;
  // 클럽글번호
  @Column(nullable = false)
  private String cbNo;
  // 게시판
  @ManyToOne
  @JoinColumn(name = "b_no")
  private Board1 board1;
}