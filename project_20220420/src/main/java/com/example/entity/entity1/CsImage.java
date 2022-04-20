package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class CsImage {
  // 스케줄이미지 코드
  private String csiImgcode;
  // 스케줄이미지 등록일
  private Date csiRegdate;
  // 스케줄이미지 
  private String csiImage;
  // 스케줄이미지 크기
  private String csiImagesize;
  // 스케줄이미지 타입
  private String csiImagetype;
  // 스케줄이미지 이름
  private String csiImagename;
}