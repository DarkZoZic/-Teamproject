package com.example.service.service_4;

import java.util.List;

import com.example.entity.entity1.Like;

import org.springframework.stereotype.Service;

@Service
public interface LikeService {

    // 일괄삭제
    public int deleteLikeBatch(List<Like> list);

    public int deleteLikeBatch1(Long[] lno);
    
}
