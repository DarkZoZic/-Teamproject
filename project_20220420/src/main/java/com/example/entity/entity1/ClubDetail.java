package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.entity.entity2.Club;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// 클럽공고
@Data
@Entity
@Table(name = "CLUBDETAIL")
@SequenceGenerator(name = "SEQ_CLUBDETAIL", sequenceName = "SEQ_CLUBDETAIL_NO", allocationSize = 1, initialValue = 1)
public class ClubDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLUBDETAIL")
  @Column(name = "CDNO")
  private Long cdno;
  // 제목
  @Column(name = "CDTITLE")
  private String cdtitle;
  // 내용
  @Column(name = "CDCONTENT")
  private String cdcontent;
  // 등록일
  @Column(name = "REGDATE")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
  private Date regdate;
  // 마감일
  @Column(name = "ENDDATE")
  private Date enddate;
  // 성별조건
  @Column(name = "GENDER")
  private String gender;
  // 연령조건
  @Column(name = "AGE")
  private String age;
  // 날짜조건
  @Column(name = "DATE")
  private String date;
  // 시간조건
  @Column(name = "TIME")
  private String time;
  // 상세내용
  @Column(name = "DESC")
  private String desc;
  // 동호회
  @ManyToOne
  @JoinColumn(name = "c_no")
  private Club club;
}
