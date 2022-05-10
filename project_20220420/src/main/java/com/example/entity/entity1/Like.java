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

import com.example.entity.entity2.Club;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "LIKETBL")
@SequenceGenerator(name = "SEQ_LIKE", sequenceName = "SEQ_LIKE_NO", allocationSize = 1, initialValue = 1)
public class Like {
  
  // 찜 번호
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LIKE")
  @Column(name = "lno")
  private Long lno;

  // 찜 등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @CreationTimestamp // CURRENT_DATE
  @Column(name = "lregdate", nullable = false)
  private Date lregdate;
  
  // 동호회
  @ManyToOne
  @JoinColumn(name = "cno")
  private Club club;
  
  // 회원
  @ManyToOne
  @JoinColumn(name = "mid")
  private Member member;
}
