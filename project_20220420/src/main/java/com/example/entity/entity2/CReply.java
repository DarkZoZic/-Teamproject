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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.entity.entity1.ClubGallery;
import com.example.entity.entity1.Member;
import com.example.entity.entity1.Reaction;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "CREPLYTBL")
@SequenceGenerator(name = "SEQ_CREPLY",
sequenceName = "SEQ_CREPLY_NO", 
allocationSize = 1, initialValue = 1)
public class CReply {

  // 댓글 번호
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_CREPLY") // 시퀀스 적용
  @Column(name = "renumber")
  private Long renumber;

  // 댓글 내용
  @Column(name = "recontent", nullable = false)
  @Lob
  private String recontent;

  // 댓글 작성일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @CreationTimestamp // CURRENT_DATE
  @Column(name = "reregdate")
  private Date reregdate;

  // 부모댓글번호
  @Column(name = "reparentnumber")
  private Long reparentnumber;

  // 수정일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@UpdateTimestamp // CURRENT_DATE
  @Column(name = "reparentnumber")
  private Date reupdatedate;

  // 댓글 공개여부
  @Column(name = "reprivate", nullable = false)
  private String reprivate;
  
  // 자유게시판 글번호
  @ManyToOne
  @JoinColumn(name = "bno")
  private Board1 board1;

  // 클럽게시판 글번호
  @ManyToOne
  @JoinColumn(name = "cbno")
  private ClubBoard clubboard;

  // 반응
  @ManyToOne
  @JoinColumn(name = "rid")
  private Reaction reaction;

  // 회원 아이디
  @ManyToOne
  @JoinColumn(name = "mid")
  private Member member;

  // 갤러리
  // @OneToMany(mappedBy = "creply")
  // @JsonBackReference
  // private List<ClubGallery> clubGalleryList = new ArrayList<>();
}