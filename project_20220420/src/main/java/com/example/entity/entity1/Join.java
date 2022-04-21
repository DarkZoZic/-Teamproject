package com.example.entity.entity1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.entity.entity2.Club;

import lombok.Data;

@Data
@Entity
@Table(name = "JOIN")
@SequenceGenerator(name = "SEQ_JOIN", sequenceName = "SEQ_JOIN_NO", allocationSize = 1, initialValue = 1)
public class Join {
  // 시퀀스
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_JOIN") // 시퀀스 적용
  private String no;
  // 동호회
  @ManyToOne
  @JoinColumn(name = "c_no")
  private Club club;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
}