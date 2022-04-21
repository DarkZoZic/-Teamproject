package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@Table(name = "QNA")
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
