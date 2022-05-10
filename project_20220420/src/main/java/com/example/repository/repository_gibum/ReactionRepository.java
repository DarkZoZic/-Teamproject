package com.example.repository.repository_gibum;

import java.util.List;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.Reaction;
import com.example.entity.entity2.Board1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository     
    extends JpaRepository<Reaction, Long>{


        // @Query(value = "SELECT * FROM"+
        // "REACTIONTBL WHERE M_ID =:id AND B_NO =: bno",nativeQuery = true)
        // public Reaction findrReaction(
        //     @Param(value = "id")String id,
        //     @Param(value ="bno")long bno
        // );
        Reaction findByBoard_Bno(long bno);
        

        Reaction findByMember_MidAndBoard_Bno(
            @Param(value = "id") String member,
            @Param(value = "bno") Long board1
        );

        // Reaction findByCount
}
