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

import com.example.entity.entity2.ClubAlbum;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "GIMAGETBL")
@SequenceGenerator(name = "SEQ_GIMAGE", sequenceName = "SEQ_GIMAGE_CODE", allocationSize = 1, initialValue = 1)
public class GImage {
  // 이미지코드
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GIMAGE")
  private Long giImgcode;

  // 등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "GIREGDATE")
	private Date giRegdate;

  // 이미지
  @Lob
  private byte[] giImage;
  // 이미지크기
  private Long giImagesize;
  // 이미지타입
  private String giImagetype;
  // 이미지이름
  private String giImagename;
  // 갤러리
  @ManyToOne
  @JoinColumn(name="gNo")
  private ClubGallery clubgallery;
  // 앨범
  @ManyToOne
  @JoinColumn(name="caNo")
  private ClubAlbum clubalbum;
}
