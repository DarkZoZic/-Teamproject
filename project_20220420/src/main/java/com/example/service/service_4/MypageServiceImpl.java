package com.example.service.service_4;

import java.util.List;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.MemberCompany;
import com.example.entity.entity1.MemberPersonal;
import com.example.entity.entity2.Board1;
import com.example.entity.entity2.CReply;
import com.example.entity.entity2.ClubBoard;
import com.example.repository.MemberRepository;
import com.example.repository.repository_4.Member1Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.net.aso.m;


@Service
public class MypageServiceImpl implements MypageService {

    MemberRepository mRepository;

    // @Override
    // public int updateMember(Member member) {
    //     try{
    //         Member uptmember = new Member();

    //         // 변경할 항목만 set
    //         uptmember.setMPhone( member.getMPhone() );
    //         uptmember.setMAddress( member.getMAddress() );
    //         uptmember.setMEmail( member.getMEmail() );
    //         uptmember.setMProfile( member.getMProfile() ); // 이미지 이렇게 해도 되나?

    //         mRepository.save(uptmember);
    //         return 1;
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return 0;
    //     }
    // }

    // @Override
    // public int updateMember1(MemberCompany memberCompany) {
    //    try{
    //        MemberCompany uptmemberco = new MemberCompany();

    //        uptmemberco.setMcNo( memberCompany.getMcNo() );
    //        uptmemberco.setMcDesc( memberCompany.getMcDesc() );
    //        uptmemberco.setMcBirth( memberCompany.getMcBirth() );

           
    //        return 1;

    //    }
    //    catch(Exception e){
    //         e.printStackTrace();
    //         return 0;
    //    }
       
    // }

    // @Override
    // public int updateMember2(MemberPersonal memberPersonal) {
    //     try{
    //         MemberPersonal uptmemberpe = new MemberPersonal();

    //         uptmemberpe.setMpNickname( memberPersonal.getMpNickname() );

    //         return 1;
 
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return 0;
    //     }
    // }

    // @Override
    // public int updatePassword(Member member) {
    //     try{
    //         Member uptmemberpw = new Member();

    //         // 변경할 항목만 set
    //         uptmemberpw.setMPw( member.getMPw() );
            
    //         mRepository.save(uptmemberpw);
    //         return 1;
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return 0;
    //     }
    // }

    // @Override
    // public int DropMember(Member member) {
    //     try{
    //         return 1;
 
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return 0;
    //     }
    // }

    // @Override
    // public Member selectMemberOne(String mId) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    // @Override
    // public List<Board1> selectMyBoard1List() {
    //     try{
    //         return ;
 
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

    // @Override
    // public List<ClubBoard> selectMyClubboardList() {
    //     try{
    //         return ;
 
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

    // @Override
    // public List<CReply> selectMyCreplyList() {
    //     try{
    //         return ;
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

    
    
}
