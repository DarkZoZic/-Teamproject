package com.example.entity.entity2;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.example.entity.entity1.ClubGallery;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class ClubAlbum {
 // 앨범번호
 @Id
 private Long caNo;
 // 앨범이름
 @Column(nullable = false)
 private String caName;
 // 앨범썸네일
 @Lob
 private String caThumbnail;
 // 생성일
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
@CreationTimestamp // CURRENT_DATE
@Column(name = "CAREGDATE",nullable = false)
 private LocalDate caRegdate;
 // 갤러리
 @OneToMany(mappedBy = "clubAlbum")
 @JsonBackReference
 private List<ClubGallery> clubGalleryList = new ArrayList<>();
}
