package com.example.service.service_4;

import org.springframework.stereotype.Service;

@Service
public interface LikeService {

    // 일괄삭제
    public int deleteLikeBatch(Long[] lno);
    
}
