package com.example.entity.entity1;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Join {
  // 시퀀스
  private String no;
  // 회원이메일
  private String mpEmail;
  // 기업아이디
  private String mcId;
  // 동호회 번호
  private String cNo;
  // 아이디
  private String mId;
}