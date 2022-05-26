package com.example.repository.repository_4;

import com.example.entity.entity1.CDCkeditor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDCkeditorRepository extends JpaRepository<CDCkeditor, Long> {


}
