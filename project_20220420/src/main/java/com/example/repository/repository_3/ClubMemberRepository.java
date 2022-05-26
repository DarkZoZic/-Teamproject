package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.entity1.JoinClub;

@Repository
public interface ClubMemberRepository extends JpaRepository<JoinClub, Long>{
	List<JoinClub> findByClub_cno(long cno);
}
