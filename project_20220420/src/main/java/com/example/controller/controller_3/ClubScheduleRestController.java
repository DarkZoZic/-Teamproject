package com.example.controller.controller_3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.entity1.CsImage;
import com.example.entity.entity2.CSchedule;
import com.example.repository.repository_3.ClubScheduleImageRepository;
import com.example.repository.repository_3.ClubScheduleRepository;

@RestController
@RequestMapping(value="/api/schedule") 
public class ClubScheduleRestController {
	@Autowired
	ClubScheduleRepository csRep;
	
	@Autowired
	ClubScheduleImageRepository csiRep;

	// 일정 등록
	// /ROOT/api/schedule/insert
	@RequestMapping(value="/insert", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertPOST(@ModelAttribute CSchedule cs, @RequestParam(name="file", required=false) MultipartFile[] file)
	{
		Map<String, Object> map = new HashMap<>();
		try
		{
			System.out.println(cs.toString());
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
		catch (Exception e) 
		{
			map.put("status", 0);
		}
		return map;
	}
	
	// 일정 목록
	// /ROOT/api/schedule/selectlist
	@RequestMapping(value="/selectlist", 
			method={RequestMethod.GET}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> selectlistGET(Model model)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			List<CSchedule> list = csRep.findAll();
			model.addAttribute("list", list);
			map.put("status", 200);
			map.put("result", model);
		} 
		catch (Exception e) 
		{
			map.put("status", 0);
		}
		return map;
	}
	
	
}
