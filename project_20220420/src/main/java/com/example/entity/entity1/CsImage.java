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

import com.example.entity.entity2.CSchedule;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table
@SequenceGenerator(name = "SEQ_CSIMAGE", sequenceName = "SEQ_CSIMAGE_CODE", allocationSize = 1, initialValue = 1)
public class CsImage {
  // 스케줄이미지 코드
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CSIMAGE")
  @Column(name = "CSIMGCODE")
  private Long csimgcode;
  
  // 스케줄이미지 등록일
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "CSIREGDATE")
	private Date csiregdate;

  // 스케줄이미지
  @Lob
  @Column(name = "CSIMAGE")
  private byte[] csimage;
  // 스케줄이미지 크기
  @Column(name = "CSIMAGESIZE")
  private Long csimagesize;
  // 스케줄이미지 타입
  @Column(name = "CSIMAGETYPE")
  private String csimagetype;
  // 스케줄이미지 이름
  @Column(name = "CSIMAGENAME")
  private String csimagename;
  // 스케줄(외래키)
  @ManyToOne
  @JoinColumn(name="sno")
  private CSchedule cschedule;
  // 클럽스케쥴
  // @OneToMany(mappedBy = "csImage")
  // @JsonBackReference
  // private List<CSchedule> cScheduleList = new ArrayList<>();
}