package com.example.repository.repository_4;

import java.util.List;

import com.example.entity.entity2.Board1;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Board1Repository extends JpaRepository<Board1, Long> {

    // 이전글 : 작은것 중에서 가장 큰값 1개
    // Board1 findTop1ByBnoLessThanOrderByBnoDesc(long bno);

    // 다음글 : 큰것 중에서 가장 작은값 1개
    // Board1 findTop1BybNoGreaterThanOrderBybNoAsc(long bno);

    // 검색어를 제목을 기준으로. 글번호 내림차순 페이지네이션  
    // List<Board1> findByBtitleContainingOrderByBnoDesc(String btitle, Pageable page);

    // long countByTitleContaining(String title);
}
