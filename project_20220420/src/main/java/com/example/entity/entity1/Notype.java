package com.example.entity.entity1;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "NOTYPE")
public class Notype {
  // 전체공지
  @Id
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
  // 알림
  @OneToOne
  @JoinColumn(name = "no_code")
  private Notification notification;
}
