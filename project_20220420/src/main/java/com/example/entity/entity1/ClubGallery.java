package com.example.entity.entity1;

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
import javax.persistence.Table;

import com.example.entity.entity2.CReply;
import com.example.entity.entity2.Club;
import com.example.entity.entity2.ClubAlbum;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "CLUBGALLERY")
@SequenceGenerator(name = "SEQ_CLUBGALLERY", sequenceName = "SEQ_CLUBGALLERY_NO", allocationSize = 1, initialValue = 1)
public class ClubGallery {
  // 갤러리번호
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLUBGALLERY")
  private String gNo;
  // 갤러리이름
  @Column(nullable = false)
  private String gName;
  // 대표이미지
  @Lob
  private byte[] gThumbnail;
  // 갤러리설명
  private String gDesc;
  // 사진수
  @Column(nullable = false)
  private String gQty;
  // 반응
  @ManyToOne
  @JoinColumn(name = "r_id")
  private Reaction reaction;
  // 갤러리이미지
  @ManyToOne
  @JoinColumn(name = "g_imgcode")
  private GImage gImage;
  // 댓글
  @ManyToOne
  @JoinColumn(name = "re_number")
  private CReply cReply;
  // 앨범
  @ManyToOne
  @JoinColumn(name = "ca_no")
  private ClubAlbum clubAlbum;
  // 동호회
  @OneToMany(mappedBy = "clubGallery")
  @JsonBackReference
  private List<Club> clubList = new ArrayList<>();
}
