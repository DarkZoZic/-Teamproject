package com.example.entity.entity2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "ADDRESSTBL")
public class Address {
  // 주소코드
  @Id
  private Long aCode;
  // 시, 도
  private String a1;
  // 구, 군
  private String a2;
  // 주소상세
  private String aDetail;
  // 동호회
  @OneToMany(mappedBy = "address")
  @JsonBackReference
  private List<Club> clubList = new ArrayList<>();
}
