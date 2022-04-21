package com.example.entity.entity2;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.entity.entity1.CsImage;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "CSCHEDULETBL")
@SequenceGenerator(name = "SEQ_CSCHEDULE",
sequenceName = "SEQ_CSCHEDULE_NO", 
allocationSize = 1, initialValue = 1)
public class CSchedule {
 // 일정번호
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_CSCHEDULE") // 시퀀스 적용
 private Long sNo;
 // 일정명
 @Column(nullable = false)
 private String sName;
 // 일정내용
 @Column(nullable = false)
 private String sContent;
 // 날짜
 @Column(nullable = false)
 private LocalDate sDate;
 // 동호회번호
 @Column(nullable = false)
 private String cNo;
 // 스케줄이미지
 @ManyToOne
 @JoinColumn(name = "csi_imgcode")
 private CsImage csImage;
 // 동호회
 @OneToMany(mappedBy = "cSchedule")
 @JsonBackReference
 private List<Club> clubList = new ArrayList<>();
}
