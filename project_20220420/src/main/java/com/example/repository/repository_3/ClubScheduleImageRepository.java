package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.entity1.CsImage;

@Repository
public interface ClubScheduleImageRepository extends JpaRepository<CsImage, Long>{
	@Query(value = "SELECT CSIMGCODE FROM CSIMAGE WHERE SNO=:sno ORDER BY CSIMGCODE ASC LIMIT :idx, 1", nativeQuery = true)
	long selectImageCode(long sno, long idx);

	void deleteByCschedule_sno(Long sno);
	
	List<CsImage> findByCschedule_snoOrderByCsimgcodeAsc(long sno);
}
