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
        // Reaction findByBoard_Bno(long bno);

        // 자유게시판
        @Query(value = "SELECT * FROM REACTIONTBL WHERE B_NO=:bno",
        countQuery = "SELECT count(*) FROM REACTIONTBL  RCOUNT",
        nativeQuery = true)
        List<Reaction> findByReaction_B_no(@Param(value = "bno")long bno);

        Reaction findByBoard1_BnoAndMember_Mid(long bno, String username);

        // 클럽게시판
        @Query(value = "SELECT * FROM REACTIONTBL WHERE CB_NO=:cbno",
        countQuery = "SELECT count(*) FROM REACTIONTBL RCOUNT",
        nativeQuery = true)
        List<Reaction> findByReaction_Cb_no(@Param(value = "cbno")long cbno);

        // 클럽갤러리
        @Query(value = "SELECT * FROM REACTIONTBL WHERE CG_NO=:cgno",
        countQuery = "SELECT count(*) FROM REACTIONTBL RCOUNT",
        nativeQuery = true)
        List<Reaction> findByReaction_Cg_no(@Param(value = "cgno")long cgno);

        // 자유게시판
        Reaction findByMember_MidAndBoard1_Bno(
            @Param(value = "id") String member,
            @Param(value = "bno") Long bno
        );
        // 클럽게시판
        Reaction findByMember_MidAndClubboard_Cbno(
            @Param(value = "id") String id,
            @Param(value = "cbno") Long cbno
        );

        // 클럽갤러리게시판
        Reaction findByMember_MidAndClubgallery_Cgno(
            @Param(value = "id") String id,
            @Param(value = "cgno") Long cgno
        );

        // Reaction findByCount

        // 자유게시판
        Reaction findByMember_midAndBoard1_bno(String mid, Long bno);

        // 댓글
        Reaction findByMember_midAndCreply_renumber(String mid, Long renumber);

}
