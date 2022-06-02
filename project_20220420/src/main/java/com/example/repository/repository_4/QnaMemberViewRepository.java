package com.example.repository.repository_4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.entity2.QnaMemberView;

@Repository
public interface QnaMemberViewRepository extends JpaRepository<QnaMemberView, Long>{
}
