package com.example.service.service_4;

import java.util.List;

import com.example.entity.entity1.Member;
import com.example.entity.entity2.Board1;
import com.example.entity.entity2.CReply;
import com.example.entity.entity2.ClubBoard;

import org.springframework.stereotype.Service;

@Service
public class MypageServiceImpl implements MypageService {

    @Override
    public int updateMember(Member member) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updatePassword(String mPw) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int DropMember(Member member) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Board1> selectMyBoard1List() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ClubBoard> selectMyClubboardList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CReply> selectMyCreplyList() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
