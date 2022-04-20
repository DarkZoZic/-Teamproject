package com.example.entity.entity2;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Board1 {
  // 글번호
  private String bNo;
  // 글제목
  private String bTitle;
  // 글내용
  private String bContent;
  // 글조회수
  private String bHit;
  // 글작성일
  private Date bRegdate;
  // 글종류
  private String bType;
  // 회원이메일
  private String mpEmail;
  // 기업아이디
  private String mcId;
  // 아이디
  private String mId;
}
