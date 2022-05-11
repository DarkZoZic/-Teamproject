package com.example.controller.controller_3;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.entity2.CbImage;
import com.example.entity.entity2.ClubBoard;
import com.example.repository.repository_3.ClubBoardImageRepository;
import com.example.repository.repository_3.ClubBoardRepository;

@RestController
@RequestMapping(value="/api/clubboard")
public class ClubBoardRestController {
	@Autowired
	ClubBoardRepository cbRep;
	
	@Autowired
	ClubBoardImageRepository cbiRep;
	
	// 클럽게시판 글작성
	// 127.0.0.1:9090/ROOT/api/clubboard/insert
	@RequestMapping(value="/insert", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertPOST(@ModelAttribute ClubBoard cb, @ModelAttribute MultipartFile file)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			cbRep.save(cb);
			if(file != null)
			{
				if(!file.isEmpty())
				{
					CbImage cbImage = new CbImage(); // 첨부한 이미지파일 받을 변수
					cbImage.setCbimage(file.getBytes()); 
					cbImage.setCbimagename(file.getOriginalFilename());
					cbImage.setCbimagesize(file.getSize());
					cbImage.setCbimagetype(file.getContentType());
					cbImage.setClubboard(cb);
					cbiRep.save(cbImage); // 이미지파일 변수에 이미지 데이터 넣고 CbImage 테이블에 저장
				}
			}
			map.put("status", 200);
		} 
		catch (Exception e) 
		{
			map.put("status", 0);
		}
		return map;
	}
	
	// src/main/resources/templates/3/clubboard/selectlist -> 조회수 증가 스크립트
	@RequestMapping(value="/updatehit", 
			method={RequestMethod.PUT}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> clubboardUpdatehit1PUT(@RequestParam(name="cbno") long cbno)
	{
		Map<String, Object> map = new HashMap<>();
		try {
			ClubBoard board = cbRep.findById(cbno).orElse(null);
			board.setCbhit( board.getCbhit() + 1L );
			cbRep.save(board);
			map.put("status", 200);
		} catch (Exception e) {
			map.put("status", 0);
		}
		return map;
	}

}
