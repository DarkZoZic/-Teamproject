package com.example.entity.entity1;

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
@Table(name = "QIMAGETBL")
@SequenceGenerator(name = "SEQ_QIMAGE",
sequenceName = "SEQ_QIMAGE_IMGCODE", 
allocationSize =  1,initialValue = 1)
public class QImage {

  // 게시판이미지코드
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_QIMAGE") // 시퀀스 적용
  @Column(name = "qimgcode")
  private Long qimgcode;

  // 게시판이미지등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
  @Column(name = "qiregdate")
  private Date qiregdate;

  // 이미지
  @Lob
  @Column(name = "qimage")
  private byte[] qimage;

  // 이미지크기
  @Column(name = "qimagesize")
  private Long qimagesize;

  // 이미지타입
  @Column(name = "qimagetype")
  private String qimagetype;

  // 이미지명
  @Column(name = "qimagename")
  private String qimagename;

  // 큐엔에이 글번호 
  @Column(name = "qno", nullable = false)
  private long qno;

  // 큐엔에이 게시판
  @ManyToOne
  @JoinColumn(name = "qna")
  private Qna qna;
}