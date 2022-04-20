package com.example.entity.entity2;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Club {
  // 동호회 번호
  private String cNo;
  // 동호회 이름
  private String cName;
  // 동호회 썸네일
  private String cThumbnail;
  // 동호회 공개여부
  private String cPrivate;
  // 동호회 설명
  private String cDesc;
  // 동호회 카테고리
  private String cCate;
  // 동호회 최대인원
  private String cMax;
  // 동호회 생성일
  private Date cRegdate;
  // 동호회 창립일
  private Date cBirth;
  // 동호회 활동지역
  private String cArea;
  // 동호회 회비
  private String cFee;
  // 일정번호
  private String sNo;
  // 글번호
  private String bNo;
  // 갤러리번호
  private String gNo;
  // 카테고리 코드
  private String cgCode;
  // 주소코드
  private String aCode;
}
