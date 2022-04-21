package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBERCOMPANY")
public class MemberCompany {
  // 기업 사업자번호
  @Id
  private String mcNo;
  // 기업설명
  @Column(nullable = false)
  private String mcDesc;
  // 기업설립일
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "MCBIRTH")
	private Date mcBirth;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
}
