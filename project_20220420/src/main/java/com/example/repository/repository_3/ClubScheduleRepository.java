package com.example.repository.repository_3;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.entity2.CSchedule;

@Repository
public interface ClubScheduleRepository extends JpaRepository<CSchedule, Long>{
	
	List<CSchedule> findByOrderBySnoDesc();
	
	List<CSchedule> findAllByStartdateBetween(LocalDateTime start, LocalDateTime end);
}
