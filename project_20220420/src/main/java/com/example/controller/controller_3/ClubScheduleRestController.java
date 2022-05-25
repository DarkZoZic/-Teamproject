package com.example.controller.controller_3;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.entity1.CsImage;
import com.example.entity.entity1.Member;
import com.example.entity.entity2.CSchedule;import com.example.jwt.JwtUtil;
import com.example.repository.repository_3.ClubScheduleImageRepository;
import com.example.repository.repository_3.ClubScheduleRepository;

@RestController
@RequestMapping(value="/api/schedule") 
public class ClubScheduleRestController {
	@Autowired
	ClubScheduleRepository csRep;
	
	@Autowired
	ClubScheduleImageRepository csiRep;
	
	@Autowired
	JwtUtil jwtUtil;

	// 일정 등록
	// /ROOT/api/schedule/insert
	@RequestMapping(value="/insert", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertPOST(@ModelAttribute CSchedule cs, @ModelAttribute MultipartFile[] file,
			@RequestHeader(name="token") String token) throws IOException
	{
		Map<String, Object> map = new HashMap<>();
		try
		{
			if(token != null)
			{
				System.out.println(cs.toString());
				Member member = new Member();
				member.setMid(jwtUtil.extractUsername(token));
				cs.setMember(member);
				csRep.save(cs);
				if(file != null)
				{
					if(file.length > 0)
					{
						for(int i=0; i<file.length; i++)
						{
							CsImage csimage = new CsImage();
							csimage.setCsimage(file[i].getBytes());
							csimage.setCsimagename(file[i].getOriginalFilename());
							csimage.setCsimagesize(file[i].getSize());
							csimage.setCsimagetype(file[i].getContentType());
							csimage.setCschedule(cs);
							csiRep.save(csimage);
						}
					}
				}
				map.put("status", 200);
			}
			else
			{
				map.put("status", 0);
				map.put("result", "토큰없음");
			}
			
		}
		catch (Exception e) 
		{
			map.put("status", -1);
		}
		return map;
	}
	
	// 일정 목록
	// /ROOT/api/schedule/selectlist
	@RequestMapping(value="/selectlist", 
			method={RequestMethod.GET}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> selectlistGET(Model model,
//			@RequestParam(name="page", defaultValue="1") int page,
			@RequestParam(name="text", defaultValue="", required=false) String text,
			@RequestParam(name="cno") long cno,
			@RequestHeader(name="token") String token)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				//1페이지 당 20글 표시
//				PageRequest pageRequest = PageRequest.of(page-1, 20); 
				
				List<CSchedule> list = csRep.findBySnameContainingAndClub_cnoOrderBySnoDesc(text, cno);
				long total = list.toArray().length;
				model.addAttribute("total", total);
				
//				model.addAttribute("pages", (total-1)/ 20 + 1);
				
				for(int i=0; i<list.toArray().length; i++)
				{
					CSchedule cs = list.get(i);
					CSchedule cSchedule = csRep.findById(cs.getSno()).orElse(null);
					cSchedule.setSthumbnail("/ROOT/schedule/image?sno=" + cs.getSno() + "&idx=0");
				}
				
				model.addAttribute("list", list);
				map.put("status", 200);
				map.put("result", model);
			}
			else
			{
				map.put("status", 0);
				map.put("result", "토큰없음");
			}
			
		}
		catch (Exception e) 
		{
			map.put("status", -1);
		}
		return map;
	}
	
	// 종료된 일정 자동 삭제 // 프론트 활용해서 자동으로 수행되게 함
	@Transactional
	@RequestMapping(value="/delete", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> delete(@RequestBody CSchedule cs)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			System.out.println("cs : " + cs);
			csiRep.deleteByCschedule_sno(cs.getSno());
			csRep.deleteById(cs.getSno());
			map.put("status", 200);
		} 
		catch (Exception e) 
		{
			map.put("status", -1);
		}
		return map;
	}
}
