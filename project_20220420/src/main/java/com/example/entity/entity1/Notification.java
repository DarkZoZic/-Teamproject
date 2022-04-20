package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Notification {
  // 알림코드
  private String noCode;
  // 보낸사람
  private String noMem;
  // 알림내용
  private String noContent;
  // 알림발생시
  private Date noDate;
  // 회원이메일
  private String mpEmail;
  // 아이디
  private String mId;
}
