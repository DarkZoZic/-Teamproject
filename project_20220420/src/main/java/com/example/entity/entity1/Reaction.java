package com.example.entity.entity1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.entity.entity2.Board1;
import com.example.entity.entity2.CReply;
import com.example.entity.entity2.ClubBoard;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "REACTIONTBL")
@SequenceGenerator(name = "SEQ_REACTION", sequenceName = "SEQ_REACTION_CODE", allocationSize = 1, initialValue = 1)
public class Reaction {
  // 반응 아이디
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REACTION")
  @Column(name = "RCODE")
  private long rcode;

  // 반응일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "RREGDATE")
  private Date rregdate;

  // 개수
  @Column(name = "RCOUNT")
  private Long rcount = 0L;

  // 좋아요 여부
  @Column(name = "liked")
  private String liked;

  // 클럽게시판
  @ManyToOne
  @JoinColumn(name = "cb_no")
  private ClubBoard clubboard;

  // 멤버아이디
  @ManyToOne
  @JoinColumn(name = "m_id")
  @ToString.Exclude //syso
  @JsonBackReference(value = "mid")
  private Member member;

  // 자유게시판
  @ManyToOne
  @JoinColumn(name = "b_no")
  @ToString.Exclude //syso
  @JsonBackReference(value = "bno")
  private Board1 board;

  // 클럽갤러리
  @ManyToOne
  @JoinColumn(name = "cg_no")
  private ClubGallery clubgallery;

  //댓글
  @ManyToOne
  @JoinColumn(name = "re_no")
  @ToString.Exclude //syso
  @JsonBackReference(value = "renumber")
  private CReply creply;


  
  // 댓글
  // @OneToMany(mappedBy = "reaction")
  // @JsonBackReference
  // private List<CReply> cReplyList = new ArrayList<>();
  // // 갤러리
  // @OneToMany(mappedBy = "reaction")
  // @JsonBackReference
  // private List<ClubGallery> clubGalleryList = new ArrayList<>();
}
