package com.example.repository.repository_4;

import com.example.entity.entity2.Board1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Board1Repository extends JpaRepository<Board1, Long> {

    //
    //List<Board1> 

   // 이전글 : 작은것 중에서 가장 큰값 1개
   //Board1 findTop1BybNoLessThanOrderBybNoDesc(long bno);

   // 다음글 : 큰것 중에서 가장 작은값 1개
   //Board1 findTop1BybNoGreaterThanOrderBybNoAsc(long bno);

}
