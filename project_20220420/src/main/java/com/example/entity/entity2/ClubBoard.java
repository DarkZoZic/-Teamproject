package com.example.entity.entity2;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.entity.entity1.Reaction;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "CLUBBOARDTBL")
@SequenceGenerator(name = "SEQ_CLUBBOARD",
sequenceName = "SEQ_CLUBBOARD_NO", 
allocationSize = 1, initialValue = 1)
public class ClubBoard {
  // 클럽글번호
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_CLUBBOARD") // 시퀀스 적용
  private Long cbNo;
  // 클럽글제목
  @Column(nullable = false)
  private String cbTitle;
  // 클럽글내용
  @Column(nullable = false)
  private String cbContent;
  // 클럽글조회수
  @Column(nullable = false)
  private Long cbHit = 0L;
  // 클럽글작성일
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
@CreationTimestamp // CURRENT_DATE
@Column(name = "CBREGDATE",nullable = false)
  private Date cbRegdate;
  // 클럽글파일첨부
  @Lob
  private byte[] cbFile;
  // 클럽글공지여부
  @Column(nullable = false)
  private String cbNoticecheck;
  // 클럽글수정일
  private Date cbUpdatedate;
  // 클럽글깊이
  @Column(nullable = false)
  private Long cbDepth = 0L;
  // 댓글
  @OneToMany(mappedBy = "clubBoard")
  @JsonBackReference
  private List<CReply> cReplyList = new ArrayList<>();
  // 게시판이미지
  @OneToMany(mappedBy = "clubBoard")
  @JsonBackReference
  private List<CbImage> cbImageList = new ArrayList<>();
  // 동호회
  @OneToMany(mappedBy = "clubBoard")
  @JsonBackReference
  private List<Club> clubList = new ArrayList<>();
  // 반응
  @OneToMany(mappedBy = "clubBoard")
  @JsonBackReference
  private List<Reaction> reactionList = new ArrayList<>();
}
