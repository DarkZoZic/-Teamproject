package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

// 후기
@Data
@Entity
@Table(name = "RATING")
public class Rating {
  // 후기코드
  @Id
  private String raCode;
  // 후기내용
  @Column(nullable = false)
  @Lob
  private String raContent;
  // 후기제목
  @Column(nullable = false)
  private String raTitle;
  // 후기작성일자
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "RAREGDATE", nullable = false)
  private Date raRegdate;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
}