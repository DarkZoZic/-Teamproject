package com.example.repository.repository_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.entity2.CReplyMemberView;

@Repository
public interface CReplyMemberViewRepository extends JpaRepository<CReplyMemberView, Long>{
	CReplyMemberView findByRenumber(long renumber);

}
