package com.example.entity.entity2;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.entity.entity1.Member;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "CHAT")
public class Chat {
  // 채팅ID
  @Id
  @Column(name = "CHID")
  private String chid;
  // 채팅 보낸사람
  @Column(name = "CHSEND")
  private String chsend;
  // 채팅 받는사람
  @Column(name = "CHRECEIVE")
  private String chreceive;
  // 채팅 첨부파일
  @Lob
  @Column(name = "CHFILE")
  private byte[] chfile;
  // 채팅 종류(개인, 단체)
  @Column(name = "CHSTATUS")
  private String chstatus;
  // 채팅 일자
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "CHREGDATE")
  private Date chregdate;
  // 회원이메일
  @Column(name = "MPEMAIL")
  private String mpemail;
  // 동호회
  @ManyToOne
  @JoinColumn(name = "c_no")
  private Club club;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
}
