package com.example.entity.entity1;

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
import javax.persistence.Table;

import com.example.entity.entity2.CReply;
import com.example.entity.entity2.Club;
import com.example.entity.entity2.ClubAlbum;

import lombok.Data;

@Data
@Entity
@Table(name = "CLUBGALLERY")
public class ClubGallery {
  // 갤러리번호
  @Id
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
  // 동호회번호
  @Column(nullable = false)
  private String cNo;
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
  private List<Club> clubList = new ArrayList<>();
}
