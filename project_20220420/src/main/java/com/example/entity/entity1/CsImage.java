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

import com.example.entity.entity2.CSchedule;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "CSIMAGETBL")
public class CsImage {
  // 스케줄이미지 코드
  @Id
  private String csiImgcode;
  // 스케줄이미지 등록일
  
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "CSIREGDATE", nullable = false)
	private Date csiRegdate;

  // 스케줄이미지
  @Column(nullable = false)
  @Lob
  private byte[] csiImage;
  // 스케줄이미지 크기
  @Column(nullable = false)
  private Long csiImagesize;
  // 스케줄이미지 타입
  @Column(nullable = false)
  private String csiImagetype;
  // 스케줄이미지 이름
  @Column(nullable = false)
  private String csiImagename;
  // 클럽스케쥴
  @OneToMany(mappedBy = "csImage")
  @JsonBackReference
  private List<CSchedule> cScheduleList = new ArrayList<>();
}