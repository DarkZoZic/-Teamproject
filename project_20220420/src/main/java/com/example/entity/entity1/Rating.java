package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// 후기
@Data
@Entity
@Table(name = "RATINGTBL")
@SequenceGenerator(name = "SEQ_RATING", sequenceName = "SEQ_RATING_CODE", allocationSize = 1, initialValue = 1)
public class Rating {
  // 후기코드
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RATING")
  @Column(name = "RACODE")
  private String racode;
  // 후기내용
  @Column(nullable = false, name = "RACONTENT")
  @Lob
  private String racontent;
  // 후기제목
  @Column(nullable = false, name = "RATITLE")
  private String ratitle;
  // 후기작성일자
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "RAREGDATE", nullable = false)
  private Date raregdate;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
}
