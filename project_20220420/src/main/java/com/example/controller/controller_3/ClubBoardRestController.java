package com.example.controller.controller_3;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.entity2.ClubBoard;
import com.example.repository.repository_3.ClubBoardRepository;

@RestController
@RequestMapping(value="/api/clubboard")
public class ClubBoardRestController {
	@Autowired
	ClubBoardRepository cbRep;
	
	// src/main/resources/templates/3/clubboard/selectlist -> 조회수 증가 스크립트
	@RequestMapping(value="/updatehit", 
			method={RequestMethod.PUT}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> clubboardUpdatehit1PUT(@RequestParam(name="cbNo") long cbNo)
	{
		Map<String, Object> map = new HashMap<>();
		try {
			ClubBoard board = cbRep.findById(cbNo).orElse(null);
			board.setCbHit( board.getCbHit() + 1L );
			cbRep.save(board);
			map.put("status", 200);
		} catch (Exception e) {
			map.put("status", 0);
		}
		return map;
	}

}
