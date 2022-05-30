package com.example.entity.entity2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.Reaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
  @Column(name = "bno")
  private Long bno;

  // 글제목
  @Column(name = "btitle")
  private String btitle;

  // 글내용
  @Column(name = "bcontent")
  @Lob
  private String bcontent;

  // 글조회수
  @Column(name = "bhit")
  private Long bhit = 0L;

  // 글작성일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "bregdate")
  private Date bregdate;

  // 글수정일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@UpdateTimestamp // 데이터를 첨부할 때 말고 update 할 때도 시간이 찍힘
  @Column(name = "buptdate")
	Date buptdate;
  
  // 글종류
  @Column(name = "btype")
  private String btype;

  // 임시변수
  @Transient
  private String bimageurl;
  
  @Transient
  private long blike;
  
  // 회원
  @ManyToOne
  @JoinColumn(name = "mid")
  private Member member;

  // 개인회원 닉네임

  // 게시판이미지
  // @OneToMany(mappedBy = "board1")
  // private List<BImage> bImageList = new ArrayList<>();

  // 댓글
  // @OneToMany(mappedBy = "board1", cascade = CascadeType.REMOVE)
  // @JsonManagedReference(value="bno")
  // private List<CReply> cReplyList = new ArrayList<>();


}
