package com.example.service.service_4;

import com.example.entity.entity2.CReply;
import com.example.repository.repository_4.CreplyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreplyServiceImpl implements CreplyService {

    @Autowired
    CreplyRepository cRepository;

    @Override
    public int insertReplyOne(CReply cReply) {
        try{
            CReply insertCreply = new CReply();
            insertCreply.setReContent(cReply.getReContent());

            cRepository.save(cReply);
            return 1;            
        }
        catch(Exception e){
            e.printStackTrace();
            return 0;
        }  
    }
    
}
