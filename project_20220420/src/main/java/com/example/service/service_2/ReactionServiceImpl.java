package com.example.service.service_2;

import com.example.entity.entity1.Reaction;
import com.example.repository.repository_gibum.ReactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionServiceImpl implements ReactionService{

    @Autowired ReactionRepository rRepository;

    @Override
    public int updateReactionRcountOne(Long bno) {

        try {

            Reaction reaction = rRepository.findById(bno).orElse(null);
            reaction.setRcount(reaction.getRcount() + 1L);
            rRepository.save(reaction);
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
}
