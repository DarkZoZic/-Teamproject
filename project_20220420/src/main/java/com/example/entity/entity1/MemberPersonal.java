package com.example.entity.entity1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBERPERSONALTBL")
@SequenceGenerator(name = "SEQ_NICKNAME",
sequenceName = "SEQ_NICKNAME_NO", 
allocationSize = 1, initialValue = 1)
public class MemberPersonal {
  // 회원닉네임
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_NICKNAME")
  private Long mpNo;

  // 닉네임 
  @Column(nullable = false)
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
