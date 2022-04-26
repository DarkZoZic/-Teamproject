package com.example.service.service_2;


import com.example.entity.entity1.Member;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    

    // 회원가입
    public int memberInsert(Member member);
}
