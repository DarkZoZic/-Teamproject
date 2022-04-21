package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@Table(name = "NOTIFICATION")
public class Notification {
  // 알림코드
  @Id
  private String noCode;
  // 보낸사람
  private String noMem;
  // 알림내용
  @Lob
  private String noContent;
  // 알림발생시
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "NODATE")
	private Date noDate;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
  // 알림종류
  @OneToOne(mappedBy = "notification")
  private Notype notype;
}
