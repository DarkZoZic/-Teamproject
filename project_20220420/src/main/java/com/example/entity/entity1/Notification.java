package com.example.entity.entity1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "NOTIFICATIONTBL")
@SequenceGenerator(name = "SEQ_NOTIFICATION", sequenceName = "SEQ_NOTIFICATION_CODE", allocationSize = 1, initialValue = 1)
public class Notification {
  // 알림코드
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTIFICATION")
  @Column(name = "NOCODE")
  private Long nocode;
  // 보낸사람
  @Column(name = "NOMEM")
  private String nomem;
  // 알림내용
  @Lob
  @Column(name = "NOCONTENT")
  private String nocontent;
  // 알림발생시
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@CreationTimestamp // CURRENT_DATE
	@Column(name = "NODATE")
	private Date nodate;
  // 회원
  @ManyToOne
  @JoinColumn(name = "m_id")
  private Member member;
  // 알림종류
  // @OneToMany(mappedBy = "notification")
  // @JsonBackReference
  // private List<Notype> notypeList = new ArrayList<>();
}
