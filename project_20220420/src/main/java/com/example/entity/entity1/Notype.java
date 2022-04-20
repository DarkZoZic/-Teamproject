package com.example.entity.entity1;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Notype {
  // 전체공지
  private String all;
  // 여러명에게
  private String many;
  // 1:1
  private String one;
  // 댓글
  private String reply;
  // 채팅
  private String chat;
  // 클럽가입수락
  private String join;
  // 알림코드
  private String noCode;
}
