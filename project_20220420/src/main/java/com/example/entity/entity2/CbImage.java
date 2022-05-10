package com.example.entity.entity2;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table
@SequenceGenerator(name = "SEQ_CBIMAGE", sequenceName = "SEQ_CBIMAGE_IMGCODE", allocationSize = 1, initialValue = 1)
public class CbImage {
  // 이미지코드
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CBIMAGE") // 시퀀스 적용
  @Column(name = "CBIMGCODE")
  private Long cbimgcode;
  // 등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @CreationTimestamp // CURRENT_DATE
  @Column(name = "CBIREGDATE")
  private Date cbiregdate;
  // 이미지
  @Lob
  @Column(name = "CBIMAGE")
  private byte[] cbimage;
  // 이미지크기
  @Column(name = "CBIMAGESIZE")
  private Long cbimagesize;
  // 이미지타입
  @Column(name = "CBIMAGETYPE")
  private String cbimagetype;
  // 이미지이름
  @Column(name = "CBIMAGENAME")
  private String cbimagename;
  // 클럽게시판
  @ManyToOne
  @JoinColumn(name = "bno")
  private ClubBoard clubboard;
}