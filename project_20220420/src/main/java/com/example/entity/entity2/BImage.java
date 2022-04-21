package com.example.entity.entity2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class BImage {
  // 게시판이미지코드
  @Id
  private Long biImgcode;
  // 게시판이미지등록일
  private Date biRegdate;
  // 게시판이미지
  @Lob
  private String biImage;
  // 게시판이미지크기
  private String biImagesize;
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