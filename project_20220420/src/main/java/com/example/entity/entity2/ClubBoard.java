package com.example.entity.entity2;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.entity.entity1.Member;

import lombok.Data;

@Data
@Entity
@Table(name = "CLUBBOARDTBL")
@SequenceGenerator(name = "SEQ_CLUBBOARD", sequenceName = "SEQ_CLUBBOARD_NO", allocationSize = 1, initialValue = 1)
public class ClubBoard {
    // 클럽글번호
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLUBBOARD") // 시퀀스 적용
    @Column(name = "CBNO")
    private Long cbno;
    // 클럽글제목
    @Column(name = "CBTITLE")
    private String cbtitle;
    // 클럽글내용
    @Column(name = "CBCONTENT")
    private String cbcontent;
    // 클럽글조회수
    @Column(name = "CBHIT")
    private Long cbhit = 0L;
  	// 클럽글작성일
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "CBREGDATE")
	private Date cbregdate;
	// 클럽글공지여부
	@Column(name = "CBNOTICECHECK")
	private String cbnoticecheck;
	// 클럽글수정일
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@UpdateTimestamp // CURRENT_DATE
	@Column(name = "CBUPDATEDATE")
    private Date cbupdatedate;
    // 클럽글깊이
    @Column(name = "CBDEPTH")
    private Long cbdepth = 0L;

	//이미지 표시용 임시 url
	@Transient
	private String cbimageurl;
	
	// 글 작성자
	@ManyToOne
	@JoinColumn(name = "mid")
	private Member member;
	
	// 클럽
	@ManyToOne
	@JoinColumn(name = "cno")
	private Club club;
  // 댓글
  // @OneToMany(mappedBy = "clubBoard")
  // @JsonBackReference
  // private List<CReply> cReplyList = new ArrayList<>();
  // // 게시판이미지
  // @OneToMany(mappedBy = "clubBoard")
  // @JsonBackReference
  // private List<CbImage> cbImageList = new ArrayList<>();
  // // 동호회
  // @OneToMany(mappedBy = "clubBoard")
  // @JsonBackReference
  // private List<Club> clubList = new ArrayList<>();
  // // 반응
  // @OneToMany(mappedBy = "clubBoard")
  // @JsonBackReference
  // private List<Reaction> reactionList = new ArrayList<>();
}
