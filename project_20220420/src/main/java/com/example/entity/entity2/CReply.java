package com.example.entity.entity2;

import static org.mockito.ArgumentMatchers.nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class CReply {
  // 댓글 번호
  @Id
  private String reNumber;
  // 댓글 내용
  @Column(nullable = false)
  @Lob
  private String reContent;
  // 댓글 작성일
  @Column(nullable = false)
  private LocalDate reRegdate;
  // 부모댓글번호
  @Column(nullable = false)
  private String rParentnumber;
  // 수정일
  private LocalDate rUpdatedate;
  // 회원이메일
  @Column(nullable = false)
  private String mpEmail;
  // 기업아이디
  @Column(nullable = false)
  private String mcId;
  // 클럽게시판
  @ManyToOne
  @JoinColumn(name = "b_no")
  private ClubBoard clubBoard;
  // 반응
  @ManyToOne
  @JoinColumn(name = "r_id")
  private Reaction reaction;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
  // 갤러리
  @OneToMany(mappedBy = "cReply")
  private List<ClubGallery> clubGalleryList = new ArrayList<>();
}