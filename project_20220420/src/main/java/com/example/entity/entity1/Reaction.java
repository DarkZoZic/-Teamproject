package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@Table(name = "REACTION")
public class Reaction {
  // 반응 아이디
  @Id
  private String rId;
  // 반응종류
  @Column(nullable = false)
  private String rType;
  // 반응일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "RREGDATE", nullable = false)
  private Date rRegdate;
  // 개수
  private String rCount;
  // 클럽게시판
  @ManyToOne
  @JoinColumn(name = "cb_no")
  private ClubBoard clubBoard;
  // 댓글
  @OneToMany(mappedBy = "reaction")
  private List<CReply> cReplyList = new ArrayList<>();
  // 갤러리
  @OneToMany(mappedBy = "reaction")
  private List<ClubGallery> clubGalleryList = new ArrayList<>();
}
