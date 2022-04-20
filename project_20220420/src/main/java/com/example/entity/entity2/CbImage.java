package com.example.entity.entity2;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class CbImage {
  // 이미지코드
  private String cbiImgcode;
  // 등록일
  private Date cbiRegdate;
  // 이미지
  private String cbiImage;
  // 이미지크기
  private String cbiImagesize;
  // 이미지타입
  private String cbiImagetype;
  // 이미지이름
  private String cbiImagename;
  // 글번호
  private String bNo;
}