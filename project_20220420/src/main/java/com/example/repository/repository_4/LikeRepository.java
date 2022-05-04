package com.example.repository.repository_4;

import java.util.List;

import com.example.entity.entity1.Like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long>{

    // 외래키, 구체적인 변수: findBy변수_하위변수
    // 주문목록 : 어떤 사람의 주문목록이니까 uemail이 필요 (고객용 주문내역)
    // 외래키 가져오기: Member_uemail -> member의 uemail
    List<Like> findByMember_mId(String mId);

    
}
