package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "QNATBL")
@SequenceGenerator(name = "SEQ_QNA", sequenceName = "SEQ_QNA_NO", allocationSize = 1, initialValue = 1)
public class Qna {
  
  // 큐엔에이 글번호
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_QNA")
  @Column(name = "qno")
  private long qno;
  
  // 큐엔에이 글제목
  @Column(name = "qtitle", nullable = false)
  private String qtitle;
  
  // 큐엔에이 글내용
  @Column(name = "qcontent", nullable = false)
  private String qcontent;
  
  // 큐엔에이 조회수
  @Column(name = "qhit", nullable = false)
  private Long qhit = 0L;
  
  // 큐엔에이 작성일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @CreationTimestamp // CURRENT_DATE
  @Column(name = "qregdate", nullable = false)
  private Date qregdate;

  // 큐엔에이 공개여부
  @Column(name = "qprivate", nullable = false)
  private String qprivate;
  
  // 회원
  @ManyToOne
  @JoinColumn(name = "mid")
  private Member member;
}
