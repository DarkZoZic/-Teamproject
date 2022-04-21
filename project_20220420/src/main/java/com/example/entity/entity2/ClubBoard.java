package com.example.entity.entity2;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.example.entity.entity1.Reaction;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class ClubBoard {
  // 클럽글번호
  @Id
  private Long cbNo;
  // 클럽글제목
  @Column(nullable = false)
  private String cbTitle;
  // 클럽글내용
  @Column(nullable = false)
  private String cbContent;
  // 클럽글조회수
  @Column(nullable = false)
  private String cbHit;
  // 클럽글작성일
  @Column(nullable = false)
  private LocalDate cbRegdate;
  // 클럽글파일첨부
  @Lob
  private String cbFile;
  // 클럽글공지여부
  @Column(nullable = false)
  private String cbNoticecheck;
  // 클럽글수정일
  private LocalDate cbUpdatedate;
  // 클럽글깊이
  @Column(nullable = false)
  private String cbDepth;
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
