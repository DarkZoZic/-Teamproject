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
@Table(name="GIMAGE")
@SequenceGenerator(name = "SEQ_GIMAGE", sequenceName = "SEQ_GIMAGE_CODE", allocationSize = 1, initialValue = 1)
public class GImage {
	  // 이미지코드
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GIMAGE")
	  @Column(name = "GIMGCODE")
	  private Long gimgcode;
	
	  // 등록일
	  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	  @CreationTimestamp // CURRENT_DATE
	  @Column(name = "GIREGDATE")
	  private Date giregdate;
	
	  // 이미지
	  @Lob
	  @Column(name = "GIMAGE")
	  private byte[] gimage;
	  // 이미지크기
	  @Column(name = "GIMAGESIZE")
	  private Long gimagesize;
	  // 이미지타입
	  @Column(name = "GIMAGETYPE")
	  private String gimagetype;
	  // 이미지이름
	  @Column(name = "GIMAGENAME")
	  private String gimagename;
	  // 갤러리
	  @ManyToOne
	  @JoinColumn(name="cgno")
	  private ClubGallery clubgallery;
	  // 앨범
	  @ManyToOne
	  @JoinColumn(name="cano")
	  private ClubAlbum clubalbum;
}
