package com.example.entity.entity2;

import java.util.Date;

import lombok.Data;

@Data
public class CReply {
  // 댓글 번호
  private String reNumber;
  // 댓글 내용
  private String reContent;
  // 댓글 작성일
  private Date reRegdate;
  // 부모댓글번호
  private String rParentnumber;
  // 수정일
  private Date rUpdatedate;
  // 글번호
  private String bNo;
  // 반응아이디
  private String rId;
  // 회원이메일
  private String mpEmail;
  // 기업아이디
  private String mcId;
  // 아이디
  private String mId;
}