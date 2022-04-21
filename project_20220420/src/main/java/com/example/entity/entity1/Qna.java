package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Qna {
  // 큐엔에이 글번호
  private String qNo;
  // 큐엔에이 글제목
  private String qTitle;
  // 큐엔에이 글내용
  private String qContent;
  // 큐엔에이 조회수
  private String qHit;
  // 큐엔에이 작성일
  private Date qRegdate;
  // 큐엔에이 파일첨부
  private String qFile;
  // 큐엔에이 깊이
  private String qDepth;
  // 큐엔에이 공개여부
  private String qPrivate;
  // 회원이메일
  private String mpEmail;
  // 기업아이디
  private String mcId;
  // 아이디
  private String mId;
}
