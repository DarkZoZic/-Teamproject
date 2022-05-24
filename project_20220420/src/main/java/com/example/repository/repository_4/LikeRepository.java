package com.example.repository.repository_4;

import java.util.List;

import javax.transaction.Transactional;

import com.example.entity.entity1.Like;
import com.example.entity.entity2.LikeProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long>{

    // 외래키, 구체적인 변수: findBy변수_하위변수
    // 주문목록 : 어떤 사람의 주문목록이니까 uemail이 필요 (고객용 주문내역)
    // 외래키 가져오기: Member_uemail -> member의 uemail

    Like findByMember_MidAndClub_Cno(
            @Param(value = "id") String member,
            @Param(value = "cno") Long cno);

    Like findByClub_cno(@Param(value = "cno")long cno);


    List<Like> findByMember_mid(String mid);

    List<LikeProjection> findByMember_midOrderByLnoAsc(String mid);

    Long findByLno(Long[] lno);

    // 일괄삭제
    @Transactional
    int deleteByMember_midAndLnoIn(String mid, Long[] lno);

    
}
