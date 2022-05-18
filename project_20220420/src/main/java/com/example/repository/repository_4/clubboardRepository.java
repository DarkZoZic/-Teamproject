package com.example.repository.repository_4;

import java.util.List;

import javax.transaction.Transactional;

import com.example.entity.entity2.ClubBoard;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface clubboardRepository extends JpaRepository<ClubBoard, Long>{
    
    // 토큰 아이디와 작성자가 같은 글
    List<ClubBoard> findByMember_midOrderByCbnoDesc(String mid, Pageable pageable);

    // 아이디로 개수 세기
    long countByMember_mid(String mid);

    // 일괄삭제
    @Transactional
    int deleteByMember_midAndCbnoIn(String mid, Long[] cbno);
}
