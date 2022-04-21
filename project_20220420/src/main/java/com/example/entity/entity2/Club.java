package com.example.entity.entity2;


import java.time.LocalDate;
import java.util.ArrayList;
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

import com.example.entity.entity1.ClubGallery;
import com.example.entity.entity1.JoinClub;
import com.example.entity.entity1.Like;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "SEQ_CLUB",
sequenceName = "SEQ_CLUB_NO", 
allocationSize = 1, initialValue = 1)
public class Club {
  // 동호회 번호
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_CLUB") // 시퀀스 적용
  private Long cNo;
  // 동호회 이름
  @Column(nullable = false)
  private byte[] cName;
  // 동호회 썸네일
  @Lob
  private String cThumbnail;
  // 동호회 공개여부
  @Column(nullable = false)
  private String cPrivate;
  // 동호회 설명
  @Column(nullable = false)
  private String cDesc;
  // 동호회 카테고리
  @Column(nullable = false)
  private String cCate;
  // 동호회 최대인원
  private String cMax;
  // 동호회 생성일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "CREGDATE",nullable = false)
  private LocalDate cRegdate;
  // 동호회 창립일
  @Column(nullable = false)
  private LocalDate cBirth;
  // 동호회 활동지역
  @Column(nullable = false)
  private String cArea;
  // 동호회 회비
  private String cFee;
  // 클럽스케쥴
  @ManyToOne
  @JoinColumn(name = "s_no")
  private CSchedule cSchedule;
  // 클럽게시판
  @ManyToOne
  @JoinColumn(name = "b_no")
  private ClubBoard clubBoard;
  // 갤러리
  @ManyToOne
  @JoinColumn(name = "g_no")
  private ClubGallery clubGallery;
  // 카테고리분류
  @ManyToOne
  @JoinColumn(name = "cg_code")
  private Category category;
  // 주소분류
  @ManyToOne
  @JoinColumn(name = "a_code")
  private Address address;
  // 찜
  @OneToMany(mappedBy = "club")
  @JsonBackReference
  private List<Like> likeList = new ArrayList<>();
  // 채팅
  @OneToMany(mappedBy = "club")
  @JsonBackReference
  private List<Chat> chatList = new ArrayList<>();
  // 클럽가입
  @OneToMany(mappedBy = "club")
  @JsonBackReference
  private List<JoinClub> joinList = new ArrayList<>();
}
