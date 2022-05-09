package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.entity1.CsImage;

@Repository
public interface ClubScheduleImageRepository extends JpaRepository<CsImage, Long>{
	@Query(value = "SELECT CSI_IMGCODE FROM CSIMAGETBL WHERE S_NO=:sNo ORDER BY CSI_IMGCODE ASC LIMIT :idx, 1", nativeQuery = true)
	long selectImageCode(long sNo, long idx);

	void deleteByCschedule_sNo(Long sNo);
	
	List<CsImage> findByCschedule_sNoOrderByCsiImgcodeAsc(long sNo);
}
