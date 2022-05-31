package com.example.entity.entity2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Data
@Entity
@Table(name="CLUBLISTVIEW")
@Immutable // 뷰 일경우 추가
public class Clublistview {

	@Id
	@Column(name="CGCODE")
	private long cgcode;

	@Column(name="CGCATE1")
	private String cgcate1;

	@Column(name="CGCATE2")
	private String cgcate2;
	
	@Column(name="CADDRESS")
	private String caddress;
	
	@Column(name="CNO")
	private long cno;
    
	@Column(name="CPRIVATE")
	private String cprivate;

	@Column(name="CNAME")
	private String cname;

	@Column(name="CDESC")
	private String cdesc;

	@Column(name="CAREA")
	private String carea;

	@Column(name="CIMAGEURL")
	private String cimageurl;
}