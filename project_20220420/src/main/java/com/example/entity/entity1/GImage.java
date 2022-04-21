package com.example.entity.entity1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "GIMAGE")
public class GImage {
  // 이미지코드
  @Id
  private String giImgcode;

  // 등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "GIREGDATE")
	private Date giRegdate;

  // 이미지
  @Column(nullable = false)
  @Lob
  private byte[] giImage;
  // 이미지크기
  @Column(nullable = false)
  private String giImagesize;
  // 이미지타입
  @Column(nullable = false)
  private String giImagetype;
  // 이미지이름
  @Column(nullable = false)
  private String giImagename;
  // 갤러리
  @OneToMany(mappedBy = "gImage")
  @JsonBackReference
  private List<ClubGallery> clubGalleryList = new ArrayList<>();
}
