package com.example.entity.entity1;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Reaction {
  // 반응 아이디
  private String rId;
  // 반응종류
  private String rType;
  // 반응일
  private Date rRegdate;
  // 개수
  private String rCount;
  // 클럽글번호
  private String cbNo;
}
