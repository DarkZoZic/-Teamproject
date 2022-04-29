package com.example.service.service_4;

import java.util.List;

import com.example.entity.entity1.Member;
import com.example.entity.entity2.Board1;
import com.example.entity.entity2.CReply;
import com.example.entity.entity2.ClubBoard;

import org.springframework.stereotype.Service;

@Service
public interface MypageService {

    // 회원정보 변경
    public int updateMember(Member member);

    // 비밀번호 변경
    public int updatePassword(String mPw);

    // 회원탈퇴
    // 아예 삭제하는게 아니라 아이디 빼고 다 업데이트 하는 방식
    public int DropMember(Member member);

    // 나의 활동
    public List<Board1> selectMyBoard1List();

    public List<ClubBoard> selectMyClubboardList();

    public List<CReply> selectMyCreplyList();

    
    
}
