package com.example.entity.entity2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Data
@Entity
@Table(name="QNAMEMBERVIEW")
@Immutable // 뷰 일경우 추가
public class QnaMemberView {
	@Id
	@Column(name="QNO")
	private long qno;
	
	@Column(name="MPNICKNAME")
	private String mpnickname;
	
	@Column(name="MCNAME")
	private String mcname;
}