package com.example.entity.entity2;

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

import com.example.entity.entity1.GImage;

import lombok.Data;

@Data
@Entity
@Table(name="CAIMAGE")
@SequenceGenerator(name="SEQ_CAIMAGE", sequenceName="SEQ_CAIMAGE_CODE", allocationSize = 1, initialValue = 1)
public class CaImage {
	@Id
	@GeneratedValue(generator = "SEQ_CAIMAGE", strategy = GenerationType.SEQUENCE)
	@Column(name="CAIMGCODE")
	private Long caimgcode;
	
	// 등록일
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name="CAIREGDATE")
	private Date cairegdate;
	
	//이미지
	@Lob
	@Column(name="CAIMAGE")
	private byte[] caimage;
	//이미지크기
	@Column(name="CAIMAGESIZE")
	private Long caimagesize;
	//이미지타입
	@Column(name="CAIMAGETYPE")
	private String caimagetype;
	//이미지파일명
	@Column(name="CAIMAGENAME")
	private String caimagename;
	
	// 앨범(외래키)
	@ManyToOne
  	@JoinColumn(name="cano")
  	private ClubAlbum clubalbum;
	
	@ManyToOne
	@JoinColumn(name="gimgcode")
	private GImage gimage;
}
