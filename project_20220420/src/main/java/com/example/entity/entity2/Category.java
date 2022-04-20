package com.example.entity.entity2;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Category {
  // 카테고리 코드
  private String cgCode;
  // 카테고리 대분류
  private String cgCate1;
  // 카테고리 상세분류
  private String cgCate2;
}
