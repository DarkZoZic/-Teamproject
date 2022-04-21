package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.entity.entity2.Club;

import lombok.Data;

@Data
@Entity
@Table(name = "LIKE")
@SequenceGenerator(name = "SEQ_LIKE", sequenceName = "SEQ_LIKE_LNO", allocationSize = 1, initialValue = 1)
public class Like {
  // 찜 번호
  @Id
  private String lNo;
  // 찜 등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @CreationTimestamp // CURRENT_DATE
  @Column(name = "LREGDATE", nullable = false)
  private Date lRegdate;
  // 동호회
  @ManyToOne
  @JoinColumn(name = "c_no")
  private Club club;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
}
