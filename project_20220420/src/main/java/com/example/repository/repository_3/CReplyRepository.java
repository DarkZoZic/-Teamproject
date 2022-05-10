package com.example.repository.repository_3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.entity2.CReply;

@Repository
public interface CReplyRepository extends JpaRepository<CReply, Long>{
	List<CReply> findByClubboard_CbnoOrderByRenumberDesc(long cbno);

	void deleteByClubboard_cbno(long cbno);
}
