package com.example.repository.repository_gibum;

import java.util.List;

import com.example.entity.entity2.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository 
    extends JpaRepository<Category, Long>{

        // List<Category> findBycgCate1(String cate);

        @Query(value = "SELECT * FROM"+
        " CATEGORYTBL WHERE CGCATE1=:cate", nativeQuery = true)
        public List<Category> cate1lList(
                @Param(value="cate") String cate);

        Category findByCgcate1(String cgcate1);
    
}
