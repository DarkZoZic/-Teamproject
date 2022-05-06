package com.example.repository.repository_gibum;

import java.util.List;

import com.example.entity.entity2.Combineaddr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CombineaddrView 
    extends JpaRepository<Combineaddr, Long>{

        // @Query(value = "SELECT * FROM"+
        // " ADDRESSTABLEVIEW WHERE A1='부산시'", nativeQuery = true)
        // public List<Combineaddr> addressesList1(
        // );

        @Query(value = "SELECT * FROM"+
        " ADDRESSTABLEVIEW WHERE A1=:add", nativeQuery = true)
        public List<Combineaddr> addressesList2(
            @Param(value = "add")String add
        );
        @Query(value = "SELECT * FROM"+
        " ADDRESSTABLEVIEW WHERE A2=:add", nativeQuery = true)
        public List<Combineaddr> addressesList3(
            @Param(value = "add")String add
        );

        List<Combineaddr> findByaCode(long aCode);
    
}
