package com.example.entity.entity1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.entity.entity2.CSchedule;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "CSIMAGETBL")
@SequenceGenerator(name = "SEQ_CSIMAGE", sequenceName = "SEQ_CSIMAGE_CODE", allocationSize = 1, initialValue = 1)
public class CsImage {
  // 스케줄이미지 코드
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CSIMAGE")
  private Long csiImgcode;
  // 스케줄이미지 등록일
  
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "CSIREGDATE")
	private Date csiRegdate;

  // 스케줄이미지
  @Lob
  private byte[] csiImage;
  // 스케줄이미지 크기
  private Long csiImagesize;
  // 스케줄이미지 타입
  private String csiImagetype;
  // 스케줄이미지 이름
  private String csiImagename;
  // 클럽스케쥴
  // @OneToMany(mappedBy = "csImage")
  // @JsonBackReference
  // private List<CSchedule> cScheduleList = new ArrayList<>();
}