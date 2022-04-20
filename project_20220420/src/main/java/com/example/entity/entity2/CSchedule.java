package com.example.entity.entity2;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class CSchedule {
  // 일정번호
  private String sNo;
  // 일정명
  private String sName;
  // 일정내용
  private String sContent;
  // 날짜
  private Date sDate;
  // 동호회번호
  private String cNo;
  // 코드
  private String csiImgcode;
}
