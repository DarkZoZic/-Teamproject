package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class MemberCompany {
  // 기업 사업자번호
  private String mcNo;
  // 기업설명
  private String mcDesc;
  // 기업설립일
  private Date mcBirth;
  // 아이디
  private String mId;
}
