package com.example.repository.repository_gibum;

import java.util.List;

import com.example.entity.entity1.CDImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CdimageRepository
        extends JpaRepository<CDImage, Long> {

    List<CDImage> findByClubDetail_cdno(long cdno);

    CDImage findByClubDetail_Cdno(long cdno);

}
