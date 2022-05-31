package com.example.repository.repository_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.entity2.Board1MemberView;

@Repository
public interface Board1MemberViewRepository extends JpaRepository<Board1MemberView, Long>{
}
