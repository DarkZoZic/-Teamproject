package com.example.entity.entity2;

import java.util.Date;

import lombok.Data;

@Data
public class Chat {
  // 채팅ID
  private String chId;
  // 채팅 보낸사람
  private String chSend;
  // 채팅 받는사람
  private String chReceive;
  // 채팅 첨부파일
  private String chFile;
  // 채팅 종류(개인, 단체)
  private String chStatus;
  // 채팅 일자
  private Date chRegdate;
  // 회원이메일
  private String mpEmail;
  // 동호회 번호
  private String cNo;
  // 아이디
  private String mId;
}
