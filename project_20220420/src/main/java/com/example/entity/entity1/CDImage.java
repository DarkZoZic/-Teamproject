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
@Table(name = "CDIMAGETBL")
@SequenceGenerator(name = "SEQ_CDIMAGE",
sequenceName = "SEQ_CDIMAGE_IMGCODE", 
allocationSize =  1,initialValue = 1)
public class CDImage {

  // 게시판이미지코드
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_CDIMAGE") // 시퀀스 적용
  @Column(name = "cdimgcode")
  private Long cdimgcode;

  // 게시판이미지등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "cdregdate")
  private Date cdregdate;

  // 게시판이미지
  @Lob
  @Column(name = "cdimage")
  private byte[] cdimage;

  // 게시판이미지크기
  @Column(name = "cdimagesize")
  private Long cdimagesize;

  // 게시판이미지타입
  @Column(name = "cdimagetype")
  private String cdimagetype;

  // 게시판이미지명
  @Column(name = "cdimagename")
  private String cdimagename;

  // 게시판
  @ManyToOne
  @JoinColumn(name = "clubdetail")
  private ClubDetail clubDetail;
}