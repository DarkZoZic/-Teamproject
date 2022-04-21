package com.example.entity.entity2;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class CbImage {
  // 이미지코드
  @Id
  private Long cbiImgcode;
  // 등록일
  @Column(nullable = false)
  private LocalDate cbiRegdate;
  // 이미지
  @Column(nullable = false)
  @Lob
  private String cbiImage;
  // 이미지크기
  @Column(nullable = false)
  private String cbiImagesize;
  // 이미지타입
  @Column(nullable = false)
  private String cbiImagetype;
  // 이미지이름
  @Column(nullable = false)
  private String cbiImagename;
  // 클럽게시판
  @ManyToOne
  @JoinColumn(name = "b_no")
  private ClubBoard clubBoard;
}