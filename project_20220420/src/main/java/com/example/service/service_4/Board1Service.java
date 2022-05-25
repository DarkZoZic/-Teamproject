package com.example.service.service_4;

import java.util.List;

import com.example.entity.entity2.Board1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface Board1Service {

    // 글쓰기
    public int insertBoard1One(Board1 board1);

    // 1개 수정
    public int updateBoard1One(Board1 board1);

    // 1개 삭제
    public int deleteBoard1One(Long bno);

    // 1개 조회
    public Board1 selectBoard1One(Long bno);

    // 검색어 + 페이지네이션
    public List<Board1> selectBoard1List(String btitle, PageRequest pageRequest);
    
    // // 페이지네이션
    // //public List<Board1> selectBoard1List()

    // 조회수 1 증가
    public int updateBoard1HitOne(Long bno); 

    // 일괄삭제
    public int deleteBoard1Batch(Long[] bno);

    // 이전글
    public long selectPrevBoard1(Long bno);

    // 다음글
    public long selectNextBoard1(Long bno);

   

    

    
    
}
