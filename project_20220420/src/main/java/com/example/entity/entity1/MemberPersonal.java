package com.example.entity.entity1;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBERPERSONAL")
public class MemberPersonal {
  // 회원닉네임
  @Id
  private String mpNickname;
  // 회원성별
  @Column(nullable = false)
  private String mpGender;
  // 회원생년월일
  @Column(nullable = false)
  private String mpBirth;
  // 회원권한
  @Column(nullable = false)
  private String mpRole;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
}
