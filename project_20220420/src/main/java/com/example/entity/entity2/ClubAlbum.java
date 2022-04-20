package com.example.entity.entity2;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class ClubAlbum {
  // 앨범번호
  private String caNo;
  // 앨범이름
  private String caName;
  // 앨범썸네일
  private String caThumbnail;
  // 생성일
  private Date caRegdate;
}
