package com.example.repository.repository_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.entity1.Reaction;

public interface ClubBoardReactionRepository extends JpaRepository<Reaction, String>{
	
	// rType(반응종류) 에 따른 각 반응의 총 갯수 각각 구하기(좋아요 ?개, 따봉 ?개, ...)
	@Query(value = "SELECT COUNT(*) FROM REACTION WHERE RTYPE=:#{#obj.rtype}", nativeQuery=true)
	public long selectReactionCount(@Param(value="obj") String rtype);
	
	
}
