package com.example.service.service_4;

import com.example.entity.entity2.Board1;

import org.springframework.stereotype.Service;

@Service
public interface Board1Service {

    public int insertBoard1One(Board1 board1);

    public int updateBoard1One(Board1 board1);

    public int deleteBoard1One(Long bNo);

    // 일괄삭제
    public int deleteBoard1Batch(Long[] bNo);

    
    
}
