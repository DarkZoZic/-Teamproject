package com.example.entity.entity1;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "LIKE")
public class Like {
  // 찜 번호
  @Id
  private String lNo;
  // 찜 등록일
  private LocalDate lRegdate;
  // 회원이메일
  @Column(nullable = false)
  private String mpEmail;
  // 동호회
  @ManyToOne
  @JoinColumn(name = "c_no")
  private Club club;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
}
