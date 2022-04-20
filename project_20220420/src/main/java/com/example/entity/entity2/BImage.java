package com.example.entity.entity2;

import java.util.Date;

import lombok.Data;

@Data
public class BImage {
  // 게시판이미지코드
  private String biImgcode;
  // 게시판이미지등록일
  private Date biRegdate;
  // 게시판이미지
  private String biImage;
  // 게시판이미지크기
  private String biImagesize;
  // 게시판이미지타입
  private String biImagetype;
  // 게시판이미지명
  private String biImagename;
  // 글번호
  private String bNo;
  // 클럽글번호
  private String cbNo;
}