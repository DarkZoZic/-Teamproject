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
        " COMBINETABLEVIEW WHERE SCODE=105 AND CNO=:no", nativeQuery = true)
        public List<ComBine> selectnumber(
                @Param(value="no") Long no);

        // M_ID로 자신의 신청내역 및 결과 조회
        @Query(value = "SELECT * FROM"+
        " COMBINETABLEVIEW WHERE  MID=:id ", nativeQuery = true)
        public List<ComBine> appdetails(
                @Param(value="id") String id);

        // 클럽멤버조회 조건 - 101,102, 104중 하나가 있어야함
        @Query(value = "SELECT * FROM"+
        " COMBINETABLEVIEW WHERE CNO=:no AND SCODE IN (101,102,104)", nativeQuery = true)
        public List<ComBine> cmemberlList(
                @Param(value="no") long no);

        // 권한변경 조회 
        @Query(value = "SELECT * FROM"+
        " COMBINETABLEVIEW WHERE  MID =:id AND CNO=:no AND SCODE IN (201,202,203,204,205)", nativeQuery = true)
        public List<ComBine> chageauth(
                @Param(value="no") long no,
                @Param(value="id") String id);

        List<ComBine> findByMid(String mid);



        
        // List<ComBine> findByScode(Long scode);
        // List<ComBine> findByOrderByScodeAsc();

    
}
