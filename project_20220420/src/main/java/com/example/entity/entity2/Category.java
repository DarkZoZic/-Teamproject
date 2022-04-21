package com.example.entity.entity2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.entity.entity1.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class Category {
 // 카테고리 코드
 @Id
 private Long cgCode;
 // 카테고리 대분류
 @Column(nullable = false)
 private String cgCate1;
 // 카테고리 상세분류
 @Column(nullable = false)
 private String cgCate2;
 // 동호회
 @OneToMany(mappedBy = "category")
 @JsonBackReference
 private List<Club> clubList = new ArrayList<>();
 // 회원
 @OneToMany(mappedBy = "category")
 @JsonBackReference
 private List<Member> memberList = new ArrayList<>();
}
