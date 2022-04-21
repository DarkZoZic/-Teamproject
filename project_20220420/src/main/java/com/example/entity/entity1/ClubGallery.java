package com.example.entity.entity1;

import javax.persistence.Entity;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class ClubGallery {
  // 갤러리번호
  private String gNo;
  // 갤러리이름
  private String gName;
  // 대표이미지
  @Lob
  private Byte[] gThumbnail;
  // 갤러리설명
  private String gDesc;
  // 사진수
  private String gQty;
  // 동호회번호
  private String cNo;
  // 반응 아이디
  private String rId;
  // 이미지코드
  private String gImgcode;
  // 댓글번호
  private String reNumber;
  // 앨범번호
  private String caNo;
}
