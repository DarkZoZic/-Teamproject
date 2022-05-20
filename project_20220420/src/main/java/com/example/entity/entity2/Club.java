package com.example.entity.entity2;


import java.time.LocalDate;
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

import com.example.entity.entity1.ClubGallery;
import com.example.entity.entity1.JoinClub;
import com.example.entity.entity1.Like;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Transient;
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
  @Column(name = "CNO")
  private Long cno;
  // 동호회 이름
  @Column(name = "CNAME", nullable = false)
  private String cname;
  // 동호회 썸네일
  @Lob
  @Column(name = "CTHUMBNAIL")
  private byte[] cthumbnail;

  @Column(name = "CIMAGENAME")
  private String cimagename;

  @Column(name = "CIMAGETYPE")
  private String cimagetype;

  @Column(name = "CIMAGESIZE")
  private Long cimagesize = 0L;

  // 동호회 공개여부
  @Column(name = "CPRIVATE", nullable = false)
  private String cprivate;
  // 동호회 설명
  @Column(name = "CDESC", nullable = false)
  private String cdesc;

  // 동호회 최대인원
  @Column(name = "CMAX")
  private String cmax;
  // 동호회 생성일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "CREGDATE",nullable = false)
  private Date cregdate;
  // 동호회 창립일
  @Column(name = "CBIRTH",nullable = true)
  private String cbirth;
  // 동호회 활동지역
  @Column(name = "CAREA", nullable = false)
  private String carea;
  // 동호회 회비
  @Column(name = "CFEE")
  private String cfee;

  @Transient
  private String cimageurl;
  
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
  @Column(name = "CADDRESS")
  private String caddress;
  // 찜
  // @OneToMany(mappedBy = "club")
  // @JsonBackReference
  // private List<Like> likeList = new ArrayList<>();
  // // 채팅
  // @OneToMany(mappedBy = "club")
  // @JsonBackReference
  // private List<Chat> chatList = new ArrayList<>();
  // // 클럽가입
  // @OneToMany(mappedBy = "club")
  // @JsonBackReference
  // private List<JoinClub> joinList = new ArrayList<>();
}
