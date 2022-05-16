package com.example.repository.repository_4;

import java.util.List;

import javax.transaction.Transactional;

import com.example.entity.entity1.Member;
import com.example.entity.entity2.Board1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Board1Repository extends JpaRepository<Board1, Long> {

    // 이전글 : 작은것 중에서 가장 큰값 1개
    Board1 findTop1ByBnoLessThanOrderByBnoDesc(Long bno);

    // 다음글 : 큰것 중에서 가장 작은값 1개
    Board1 findTop1ByBnoGreaterThanOrderByBnoAsc(long bno);

    // 검색어를 제목을 기준으로. 글번호 내림차순 페이지네이션  
    // List<Board1> findByBtitleContainingOrderByBnoDesc(String btitle, Pageable pageable);

    // List<Board1> findbyBtitleContaining(String bTitle);

    // 검색어가 포함된 전체 개수
    // SELECT COUNT(*) FROM BOARD10 
    // WHERE BTITLE LIKE '%' || '검색어' || '%' 
    long countByBtitleContaining(String btitle);

    // member가 작성한 글을 내림차순으로 조회
    // Page<Board1> findByMemberOrderByBNoDesc(Member member, Pageable pageable);

    // 회원 목록 + 검색
    // Page<Board1> findAll(Pageable pageable);

    


    // 검색(글제목 기준) + 글번호 내림차순 페이지네이션
    List<Board1> findByBtitleContainingOrderByBnoDesc(String btitle, Pageable pageable);

    // 검색(내용) + 글번호 내림차순 페이지네이션
    List<Board1> findByBcontentContainingOrderByBnoDesc(String bcontent, Pageable pageable);
    
    // 검색(작성자) + 글번호 내림차순 페이지네이션
    List<Board1> findByMember_midContainingOrderByBnoDesc(String mid, Pageable pageable);

    // 검색(제목, 내용, 작성자) + 글번호 내림차순 페이지네이션
    List<Board1> findByBcontentOrMember_midOrBtitleContainingOrderByBnoDesc(String btitle, String bcontent, String mid, Pageable pageable);
    
    // 글목록
    List<Board1> findAllByOrderByBnoDesc(Pageable pageable);
    
    // 일괄삭제
    @Transactional
    int deleteByMember_midAndBnoIn(String mid, Long[] bno);

  


}
