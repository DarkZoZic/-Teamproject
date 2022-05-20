package com.example.repository.repository_gibum;

import java.util.List;

import com.example.entity.entity2.Cimage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CimageRepository
    extends JpaRepository< Cimage, Long > {

        Cimage findByClub_Cno(long cno);

        // List<Cimage> findByClub_CnoList(long cno);
    
}
