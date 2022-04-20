package com.example.entity.entity2;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class ClubBoard {
  // 클럽글번호
  private String cbNo;
  // 클럽글제목
  private String cbTitle;
  // 클럽글내용
  private String cbContent;
  // 클럽글조회수
  private String cbHit;
  // 클럽글작성일
  private Date cbRegdate;
  // 클럽글파일첨부
  private String cbFile;
  // 클럽글공지여부
  private String cbNoticecheck;
  // 클럽글수정일
  private Date cbUpdatedate;
  // 클럽글깊이
  private String cbDepth;
}
