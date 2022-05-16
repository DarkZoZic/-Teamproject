package com.example.entity.entity1;

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
import org.springframework.format.annotation.DateTimeFormat;

import com.example.entity.entity2.CReply;

import lombok.Data;

@Data
@Entity
@Table(name = "CLUBGALLERY")
@SequenceGenerator(name = "SEQ_CLUBGALLERY", sequenceName = "SEQ_CLUBGALLERY_NO", allocationSize = 1, initialValue = 1)
public class ClubGallery {
  // 갤러리번호
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLUBGALLERY")
  @Column(name = "CGNO")
  private Long cgno;
  // 갤러리이름
  @Column(name = "CGNAME")
  private String cgname;
  // 갤러리설명
  @Column(name = "CGDESC")
  private String cgdesc;
  //조회수(기본값 = 0)
  @Column(name="CGHIT")
  private Long cghit = 0L;
  // 등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
@CreationTimestamp // CURRENT_DATE
@Column(name = "CGREGDATE")
  private Date cgregdate;
  // 반응
  @ManyToOne
  @JoinColumn(name = "rid")
  private Reaction reaction;
  // 갤러리이미지
//  @OneToMany(mappedBy = "GImage")
//  private GImage gImage;
  // 댓글
  @ManyToOne
  @JoinColumn(name = "renumber")
  private CReply creply;
  // 앨범
//  @ManyToOne
//  @JoinColumn(name = "ca_no")
//  private ClubAlbum clubAlbum;
  // 동호회
  // @OneToMany(mappedBy = "clubGallery")
  // @JsonBackReference
  // private List<Club> clubList = new ArrayList<>();
}
