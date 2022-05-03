package com.example.service.service_4;

import com.example.entity.entity2.CReply;

import org.springframework.stereotype.Service;

@Service
public interface CreplyService {

    // 댓글쓰기
    public int insertReplyOne(CReply cReply);
    
}
