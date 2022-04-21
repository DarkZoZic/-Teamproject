package com.example.entity.entity1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "NOTYPETBL")
public class Notype {
  // 전체공지
  @Id
  @Column(name = "allnotice")
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
  @Column(name = "joinclub")
  private String join;
  // 알림
  @OneToMany
  @JsonBackReference
  private Notification notification;
}
