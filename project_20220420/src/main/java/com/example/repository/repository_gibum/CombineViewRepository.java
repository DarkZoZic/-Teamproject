package com.example.repository.repository_gibum;

import java.util.List;

import com.example.entity.entity2.ComBine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CombineViewRepository 
    extends JpaRepository<ComBine, Long>{
        // 클럽번호로 클럽신청내역 조회
        @Query(value = "SELECT * FROM"+
        " COMBINETABLEVIEW WHERE S_CODE=105 AND C_NO=:no", nativeQuery = true)
        public List<ComBine> selectnumber(
                @Param(value="no") Long no);

        // M_ID로 자신의 신청내역 및 결과 조회
        @Query(value = "SELECT * FROM"+
        " COMBINETABLEVIEW WHERE  M_ID=:id ", nativeQuery = true)
        public List<ComBine> appdetails(
                @Param(value="id") String id);

        // 클럽멤버조회 조건 - 101,102, 104중 하나가 있어야함
        @Query(value = "SELECT * FROM"+
        " COMBINETABLEVIEW WHERE C_NO=:no AND S_CODE IN (101,102,104)", nativeQuery = true)
        public List<ComBine> cmemberlList(
                @Param(value="no") long no);

        // 권한변경 조회 
        @Query(value = "SELECT * FROM"+
        " COMBINETABLEVIEW WHERE  M_ID =:id AND C_NO=:no AND S_CODE IN (201,202,203,204,205)", nativeQuery = true)
        public List<ComBine> chageauth(
                @Param(value="no") long no,
                @Param(value="id") String id);

        ComBine findTop1ByNoGreaterThanOrderByNoDesc(long no);



        
        List<ComBine> findByScode(Long scode);
        List<ComBine> findByOrderByScodeAsc();

    
}
