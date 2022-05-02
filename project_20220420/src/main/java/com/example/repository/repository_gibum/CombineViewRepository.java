package com.example.repository.repository_gibum;

import java.util.List;

import com.example.entity.entity2.ComBine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CombineViewRepository 
    extends JpaRepository<ComBine, Long>{

        // @Query(value = "SELECT S_CODE FROM COMBINETABLEVIEW WHERE S_CODE=:no", nativeQuery = true)
        // public List<ComBine> selectnumber(
        //         @Param(value="no") Long no);

        ComBine findTop1ByNoGreaterThanOrderByNoDesc(long no);

        
        List<ComBine> findByScode(Long scode);
        List<ComBine> findByOrderByScodeAsc();

    
}
