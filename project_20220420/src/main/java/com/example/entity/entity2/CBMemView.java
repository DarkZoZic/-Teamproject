package com.example.entity.entity2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Data
@Entity
@Table(name="CBMEMVIEW")
@Immutable
public class CBMemView {
	@Id
	@Column(name="CBNO")
	private long cbno;
	
	@Column(name="MPNICKNAME")
	private String mpnickname;
	
	@Column(name="MCNAME")
	private String mcname;
}
