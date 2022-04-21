package com.example.service.service_2;


import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    

    // 회원탈퇴
    public int memberDeleteOne(String email);
}
