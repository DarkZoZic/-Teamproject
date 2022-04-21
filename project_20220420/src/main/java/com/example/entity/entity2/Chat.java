package com.example.entity.entity2;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.example.entity.entity1.Member;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Chat {
  // 채팅ID
  @Id
  private String chId;
  // 채팅 보낸사람
  @Column(nullable = false)
  private String chSend;
  // 채팅 받는사람
  @Column(nullable = false)
  private String chReceive;
  // 채팅 첨부파일
  @Column(nullable = false)
  @Lob
  private String chFile;
  // 채팅 종류(개인, 단체)
  @Column(nullable = false)
  private String chStatus;
  // 채팅 일자
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "BREGDATE",nullable = false)
  private LocalDate chRegdate;
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
