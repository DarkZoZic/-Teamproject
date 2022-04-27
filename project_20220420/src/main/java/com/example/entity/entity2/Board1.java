package com.example.entity.entity2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.entity.entity1.Member;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "BOARD1TBL")
@SequenceGenerator(name = "SEQ_BOARD_1",
sequenceName = "SEQ_BOARD_1_NO", 
allocationSize = 1, initialValue = 1)
public class Board1 {
  // 글번호
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_BOARD_1") // 시퀀스 적용
  private Long bNo;
  // 글제목
  private String bTitle;
  // 글내용
  private String bContent;
  // 글조회수
  private String bHit;
  // 글작성일

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "BREGDATE")
  private Date bRegdate;
  // 글종류
  private String bType;
  // 회원이메일
  // @Column(nullable = false)
  private String mpEmail;
  // 기업아이디
  // @Column(nullable = false)
  private String mcId;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
  // 게시판이미지
  // @OneToMany(mappedBy = "board1")
  // private List<BImage> bImageList = new ArrayList<>();
}
