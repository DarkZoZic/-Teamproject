package com.example.repository.repository_4;

import java.util.List;

import com.example.entity.entity1.Qna;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long> {

    // 글목록
    List<Qna> findAllByOrderByQnoDesc(Pageable pageable);

    // 검색(제목) + 글번호 내림차순 페이지네이션
    List<Qna> findByQtitleContainingOrderByQnoDesc(String qtitle, Pageable pageable);

    // 검색(내용) + 글번호 내림차순 페이지네이션
    List<Qna> findByQcontentContainingOrderByQnoDesc(String qcontent, Pageable pageable);
    
    // 검색(작성자) + 글번호 내림차순 페이지네이션
    List<Qna> findByMember_midContainingOrderByQnoDesc(String mid, Pageable pageable);

    // 검색(제목, 내용, 작성자) + 글번호 내림차순 페이지네이션
    List<Qna> findByQcontentOrMember_midOrQtitleContainingOrderByQnoDesc(String qtitle, String qcontent, String mid, Pageable pageable);
    
}
