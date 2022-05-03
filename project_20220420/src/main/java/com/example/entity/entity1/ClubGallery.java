package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
  private Long gNo;
  // 갤러리이름
  private String gName;
  // 대표이미지
  @Lob
  private byte[] gThumbnail;
  // 갤러리설명
  private String gDesc;
  // 등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
@CreationTimestamp // CURRENT_DATE
  private Date gRegdate;
  // 반응
  @ManyToOne
  @JoinColumn(name = "r_id")
  private Reaction reaction;
  // 갤러리이미지
//  @OneToMany(mappedBy = "GImage")
//  private GImage gImage;
  // 댓글
  @ManyToOne
  @JoinColumn(name = "re_number")
  private CReply cReply;
  // 앨범
//  @ManyToOne
//  @JoinColumn(name = "ca_no")
//  private ClubAlbum clubAlbum;
  // 동호회
  // @OneToMany(mappedBy = "clubGallery")
  // @JsonBackReference
  // private List<Club> clubList = new ArrayList<>();
}
