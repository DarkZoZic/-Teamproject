package com.example.entity.entity1;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class MemberPersonal {
  // 회원닉네임
  private String mpNickname;
  // 회원성별
  private String mpGender;
  // 회원생년월일
  private String mpBirth;
  // 회원권한
  private String mpRole;
  // ?프로필사진
  private String mpProfile;
  // 아이디
  private String mId;
}
