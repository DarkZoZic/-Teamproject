package com.example.entity.entity2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Board1 {
  // 글번호
  @Id
  private String bNo;
  // 글제목
  private String bTitle;
  // 글내용
  private String bContent;
  // 글조회수
  private String bHit;
  // 글작성일
  private LocalDate bRegdate;
  // 글종류
  private String bType;
  // 회원이메일
  @Column(nullable = false)
  private String mpEmail;
  // 기업아이디
  @Column(nullable = false)
  private String mcId;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
  // 게시판이미지
  @OneToMany(mappedBy = "board1")
  private List<BImage> bImageList = new ArrayList<>();
}
