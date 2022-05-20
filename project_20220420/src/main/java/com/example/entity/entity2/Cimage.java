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
@Table(name = "CIMAGETBL")
@SequenceGenerator(name = "SEQ_CIMAGE",
sequenceName = "SEQ_CIMAGE_IMGCODE", 
allocationSize =  1,initialValue = 1)
public class Cimage {

  // 클럽썸내일 코드
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_CIMAGE") // 시퀀스 적용
  @Column(name = "CIMAGECODE")
  private Long cimagecode;

  // 썸내일 등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "CREGDATE")
  private Date cregdate;

  // 클럽썸내일 이미지
  @Lob
  @Column(name = "CIMAGE")
  private byte[] cimage;

  // 클럽썸내일 이미지크기
  @Column(name = "CIMAGESIZE")
  private Long cimagesize;

  // 클럽썸내일 이미지타입
  @Column(name = "CIMAGETYPE")
  private String cimagetype;

  // 클럽썸내일 이미지명
  @Column(name = "CIMAGENAME")
  private String cimagename;

  // 클럽
  @ManyToOne
  @JoinColumn(name = "club")
  private Club club;
}