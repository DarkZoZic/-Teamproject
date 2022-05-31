package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBERCOMPANYTBL")
public class MemberCompany {
  // 기업 사업자번호
  @Id
  @Column(name = "MCNO")
  private long mcno;

  // 기업설명
  @Column(name = "MCDESC", nullable = false)
  private String mcdesc;

  // 기업이름
  @Column(name = "MCNAME", nullable = false)
  private String mcname;
  
  // 기업설립일
	@Column(name = "MCBIRTH")
	private String mcbirth;

  // 회원권한
  @Column(name = "MCROLE")
  private String mcrole;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
}
