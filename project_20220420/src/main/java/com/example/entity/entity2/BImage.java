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
@Table(name = "BIMAGETBL")
@SequenceGenerator(name = "SEQ_BIMAGE",
sequenceName = "SEQ_BIMAGE_IMGCODE", 
allocationSize =  1,initialValue = 1)
public class BImage {

  // 게시판이미지코드
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_BIMAGE") // 시퀀스 적용
  @Column(name = "biimgcode")
  private Long biimgcode;

  // 게시판이미지등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "biregdate")
  private Date biregdate;

  // 게시판이미지
  @Lob
  @Column(name = "biimage")
  private byte[] biimage;

  // 게시판이미지크기
  @Column(name = "biimagesize")
  private Long biimagesize;

  // 게시판이미지타입
  @Column(name = "biimagetype")
  private String biimagetype;

  // 게시판이미지명
  @Column(name = "biimagename")
  private String biimagename;

  // 게시판
  @ManyToOne
  @JoinColumn(name = "board1")
  private Board1 board1;
}