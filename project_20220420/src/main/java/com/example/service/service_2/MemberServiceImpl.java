package com.example.service.service_2;


import com.example.entity.entity1.Member;
import com.example.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl  implements MemberService{

    @Autowired MemberRepository mRepository;

    @Override
    public int memberInsert(Member member) {
        try{
            mRepository.save(member);
            return 1;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
            }
    }
    

    
   
}
    


    
