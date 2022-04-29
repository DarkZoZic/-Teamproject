package com.example.entity.entity1;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

// 클럽활동내역
@Data
@Entity
@Table(name = "STEPTBL")
public class Steptbl {
  // 개설 신청 가입 관리자승격 
  @Id
  private Long sCode;
  // 내용(설명)
  private String sContent;
  // 클럽가입
  @OneToMany(mappedBy = "steptbl")
  private List<JoinClub> joinclubList = new ArrayList<>();
}