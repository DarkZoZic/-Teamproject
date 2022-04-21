package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Member {
  // 아이디
  private String mId;
  // 비밀번호
  private String mPw;
  // 이름
  private String mName;
  // 연락처
  private String mPhone;
  // 주소
  private String mAddress;
  // 가입일
  private Date mRegdate;
  // 프로필사진
  private String mProfile;
  // 이메일
  private String mEmail;
  // 카테고리 코드
  private String cgCode;
}