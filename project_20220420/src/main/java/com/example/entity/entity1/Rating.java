package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Rating {
  // 후기코드
  private String raCode;
  // 후기내용
  private String raContent;
  // 후기제목
  private String raTitle;
  // CURRENT_DATE
  private Date raRegdate;
  // 회원이메일
  private String mpEmail;
  // 아이디
  private String mId;
}
