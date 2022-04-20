package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class GImage {
  // 이미지코드
  private String giImgcode;
  // 등록일
  private Date giRegdate;
  // 이미지
  private String giImage;
  // 이미지크기
  private String giImagesize;
  // 이미지타입
  private String giImagetype;
  // 이미지이름
  private String giImagename;
}
