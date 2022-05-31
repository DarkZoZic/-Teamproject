package com.example.repository.repository_gibum;

import java.util.List;

import com.example.entity.entity2.Clublistview;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubListViewRepository 
    extends JpaRepository<Clublistview, Long>{
 
        List<Clublistview> findByCprivateAndCgcate1OrCgcate2(String ad, String cprivate, String cate);
        
        List<Clublistview> findByCprivateOrderByCnoDesc(String cprivate);

        List<Clublistview> findByCprivateAndCaddressContaining(String cprivate, String address);
        
}
