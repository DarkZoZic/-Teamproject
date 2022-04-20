package com.example.entity;


import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class AddressEntity {
  // 주소코드
  private String aCode;
  // 시, 도
  private String a1;
  // 구, 군
  private String a2;
  // 주소상세
  private String aDetail;
}
