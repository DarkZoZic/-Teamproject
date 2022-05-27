package com.example.controller.controller_3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.entity1.JoinClub;
import com.example.entity.entity1.MemberPersonal;
import com.example.repository.repository_3.ClubMemberRepository;
import com.example.repository.repository_3.PersonalMemberRepository;

@RestController
@RequestMapping(value="/api/clubmember")
public class ClubMemberRestController {
	@Autowired
	ClubMemberRepository cmRep;
	
	@Autowired
	PersonalMemberRepository pmRep;	
	// 클럽별 가입회원 목록
	@RequestMapping(value="/selectmemberlist", 
			method={RequestMethod.GET}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> selectmemberGET(@RequestParam(name="cno") long cno)
	{
		Map<String, Object> map = new HashMap<>();
		try
		{
			List<JoinClub> memberlist = cmRep.findByClub_cno(cno);
			map.put("status", 200);
			map.put("result", memberlist);
		}
		catch (Exception e)
		{
			map.put("status", -1);
		}
		return map;
	}
	
	// mid 목록 받아서 닉네임 목록 반환(배열)
//	@RequestMapping(value="/selectnicklist", 
//			method={RequestMethod.GET}, 
//			consumes = {MediaType.ALL_VALUE},
//			produces= {MediaType.APPLICATION_JSON_VALUE})
//	public Map<String, Object> selectnicklistGET(@RequestParam(name="mid") String[] mid)
//	{
//		Map<String, Object> map = new HashMap<>();
//		try 
//		{
//			System.out.println(mid);
//			List<MemberPersonal> mp = pmRep.findByMember_midOrderByMpnicknameAsc(mid);
//			map.put("status", 200);
//			map.put("result", mp);
//		}
//		catch (Exception e)
//		{
//			map.put("status", -1);
//		}
//		return map;
//	}
		
	// mid 받아서 닉네임 반환(단일)
	@RequestMapping(value="/selectnick", 
			method={RequestMethod.GET}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> selectnickGET(@RequestParam(name="mid") String mid)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			MemberPersonal mp = pmRep.findByMember_mid(mid);
			map.put("status", 200);
			map.put("result", mp);
		}
		catch (Exception e)
		{
			map.put("status", -1);
		}
		return map;
	}

}
