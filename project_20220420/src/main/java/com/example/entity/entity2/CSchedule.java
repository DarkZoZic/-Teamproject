package com.example.entity.entity2;

import java.time.LocalDateTime;
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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.entity.entity1.Member;

import lombok.Data;

@Data
@Entity
@Table(name = "CSCHEDULETBL")
@SequenceGenerator(name = "SEQ_CSCHEDULE", sequenceName = "SEQ_CSCHEDULE_NO", allocationSize = 1, initialValue = 1)
public class CSchedule {
 // 일정번호
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CSCHEDULE") // 시퀀스 적용
 @Column(name = "SNO")
 private Long sno;
 // 일정명
 @Column(name = "SNAME")
 private String sname;
 // 일정내용
 @Column(name = "SCONTENT")
 private String scontent;
 // 일정 시작일시(분단위까지)
 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
 @Column(name = "STARTDATE")
 private LocalDateTime startdate;
 // 일정 예상종료일시(분단위)
 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
 @Column(name = "ENDDATE")
 private LocalDateTime enddate;
 // 일정 등록일
 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
 @CreationTimestamp //CURRENT_DATE
 @Column(name= "SREGDATE")
 private Date sregdate;
 // 썸네일 임시변수
 @Transient
 private String sthumbnail;
 // 동호회번호
 @ManyToOne
 @JoinColumn(name = "cno")
 private Club club;
 // 작성자명
 @ManyToOne
 @JoinColumn(name = "mid")
 private Member member;
 // 동호회
//  @OneToMany(mappedBy = "cSchedule")
//  @JsonBackReference
//  private List<Club> clubList = new ArrayList<>();
}
