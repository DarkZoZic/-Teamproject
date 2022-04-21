package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "QNATBL")
public class Qna {
  // 큐엔에이 글번호
  @Id
  private String qNo;
  // 큐엔에이 글제목
  @Column(nullable = false)
  private String qTitle;
  // 큐엔에이 글내용
  @Column(nullable = false)
  private String qContent;
  // 큐엔에이 조회수
  @Column(nullable = false)
  private Long qHit;
  // 큐엔에이 작성일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @CreationTimestamp // CURRENT_DATE
  @Column(name = "QREGDATE", nullable = false)
  private Date qRegdate;
  // 큐엔에이 파일첨부 ???
  @Lob
  private String qFile;
  // 큐엔에이 공개여부
  @Column(nullable = false)
  private String qPrivate;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
}
