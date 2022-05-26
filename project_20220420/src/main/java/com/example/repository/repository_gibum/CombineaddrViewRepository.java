package com.example.repository.repository_gibum;

import java.util.List;

import com.example.entity.entity2.Combineaddr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CombineaddrViewRepository 
    extends CrudRepository<Combineaddr, Long>{

        // @Query(value = "SELECT * FROM"+
        // " ADDRESSTABLEVIEW WHERE A1='부산시'", nativeQuery = true)
        // public List<Combineaddr> addressesList1(
        // );

        List<Combineaddr> findByCgcate1(String cate);

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

        // List<Combineaddr> findByaCode(long aCode);


        
        // @Query(value = "SELECT * FROM"+
        // " ADDRESSTABLEVIEW WHERE A1=:address", nativeQuery = true)
        // public List<Combineaddr> addrlist(
        //         @Param(value="address") String address);

        // @Query(value = "SELECT * FROM"+
        // " ADDRESSTABLEVIEW WHERE A2=:address", nativeQuery = true)
        // public List<Combineaddr> addrlist2(
        //         @Param(value="address") String address);

        @Query(value = "SELECT * FROM"+
        " ADDRESSTABLEVIEW WHERE CGCATE1=:cate", nativeQuery = true)
        public List<Combineaddr> CateSearch(
                @Param(value="cate") String cate);

        @Query(value = "SELECT * FROM"+
        " ADDRESSTABLEVIEW WHERE CGCATE2=:cate", nativeQuery = true)
        public List<Combineaddr> CateSearch2(
                @Param(value="cate") String cate);

        @Query(value = "SELECT * FROM"+
        " ADDRESSTABLEVIEW WHERE CNAME LIKE %:title% ORDER BY CNO DESC", nativeQuery = true)
        public List<Combineaddr> Search(
                @Param(value="title") String title);

        @Query(value = "SELECT * FROM"+
        " ADDRESSTABLEVIEW WHERE CGCATE2 LIKE %:title% ORDER BY CNO DESC", nativeQuery = true)
        public List<Combineaddr> Search2(
                @Param(value="title") String title);


        // List<Combineaddr> findBycNameContainingOrderBycNoDesc(String title);

        // List<Combineaddr> findBya1(String address);
    
}
