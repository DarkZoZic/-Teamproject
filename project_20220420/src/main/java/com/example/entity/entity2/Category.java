package com.example.entity.entity2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.entity.entity1.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "CATEGORYTBL")
@SequenceGenerator(name = "SEQ_CATEGORY",
sequenceName = "SEQ_CATEGORY_CODE", 
allocationSize = 1, initialValue = 1)
public class Category {
 // 카테고리 코드
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, 
  generator = "SEQ_CATEGORY") // 시퀀스 적용
  @Column(name = "CGCODE")
 private Long cgcode;
 // 카테고리 대분류
 @Column(name = "CGCATE1", nullable = false)
 private String cgcate1;
 // 카테고리 상세분류
 @Column(name = "CGCATE2", nullable = false)
 private String cgcate2;
 // 동호회
// @OneToMany(mappedBy = "category")
// @JsonBackReference
// private List<Club> clubList = new ArrayList<>();
// // 회원
// @OneToMany(mappedBy = "category")
// @JsonBackReference
// private List<Member> memberList = new ArrayList<>();
}
