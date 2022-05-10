package com.example.entity.entity2;

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

import com.example.entity.entity1.CsImage;

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
 // 날짜
 @Column(name = "SDATE")
 private Date sdate;
 // 동호회번호
 @ManyToOne
 @JoinColumn(name = "cno")
 private Club club;
 // 스케줄이미지
 @ManyToOne
 @JoinColumn(name = "csimgcode")
 private CsImage csimage;
 // 동호회
//  @OneToMany(mappedBy = "cSchedule")
//  @JsonBackReference
//  private List<Club> clubList = new ArrayList<>();
}
