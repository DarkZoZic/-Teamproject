package com.example.entity.entity1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.entity.entity2.CReply;
import com.example.entity.entity2.ClubBoard;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "REACTIONTBL")
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
  @JsonBackReference
  private List<CReply> cReplyList = new ArrayList<>();
  // 갤러리
  @OneToMany(mappedBy = "reaction")
  @JsonBackReference
  private List<ClubGallery> clubGalleryList = new ArrayList<>();
}
