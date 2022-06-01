package com.example.entity.entity1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Data
@Entity
@Table(name="CGMEMVIEW")
@Immutable
public class CGMemView {
	@Id
	@Column(name="CGNO")
	private long cgno;
	
	@Column(name="MPNICKNAME")
	private String mpnickname;
	
	@Column(name="MCNAME")
	private String mcname;
}
