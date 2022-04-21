package com.example.entity.entity1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.entity.entity2.Board1;
import com.example.entity.entity2.CReply;
import com.example.entity.entity2.Category;
import com.example.entity.entity2.Chat;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBERTBL")
public class Member {
  // 아이디
  @Id
  private String mId;
  // 비밀번호
  @Column(nullable = false)
  private String mPw;
  // 이름
  @Column(nullable = false)
  private String mName;
  // 연락처
  @Column(nullable = false)
  private String mPhone;
  // 주소
  @Column(nullable = false)
  private String mAddress;
  // 가입일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "MREGDATE", nullable = false)
	private Date mRegdate;
  // 프로필사진
  @Lob
  private byte[] mProfile;
  // 이메일
  @Column(nullable = false)
  private String mEmail;
  // 카테고리분류
  @ManyToOne
  @JoinColumn(name = "cg_code")
  private Category category;
  // 알림
  @OneToMany(mappedBy = "member")
  @JsonBackReference
  private List<Notification> notificationList = new ArrayList<>();
  // 클럽가입
  @OneToMany(mappedBy = "member")
  @JsonBackReference
  private List<JoinClub> joinList = new ArrayList<>();
  // 댓글
  @OneToMany(mappedBy = "member")
  @JsonBackReference
  private List<CReply> cReplyList = new ArrayList<>();
  // QNA
  @OneToMany(mappedBy = "member")
  @JsonBackReference
  private List<Qna> qnaList = new ArrayList<>();
  // 찜
  @OneToMany(mappedBy = "member")
  @JsonBackReference
  private List<Like> likeList = new ArrayList<>();
  // 채팅
  @OneToMany(mappedBy = "member")
  @JsonBackReference
  private List<Chat> chatList = new ArrayList<>();
  // 게시판
  @OneToMany(mappedBy = "member")
  @JsonBackReference
  private List<Board1> board1List = new ArrayList<>();
  // 후기
  @OneToMany(mappedBy = "member")
  @JsonBackReference
  private List<Rating> ratingList = new ArrayList<>();
  // 개인회원
  @OneToMany(mappedBy = "member")
  @JsonBackReference
  private List<MemberPersonal> memberPersonalList = new ArrayList<>();
  // 기업회원
  @OneToMany(mappedBy = "member")
  @JsonBackReference
  private List<MemberCompany> memberCompanyList = new ArrayList<>();
}