package com.example.controller.controller_3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.entity1.CsImage;
import com.example.entity.entity2.CSchedule;
import com.example.repository.repository_3.ClubScheduleImageRepository;
import com.example.repository.repository_3.ClubScheduleRepository;

@Controller
@RequestMapping(value="/clubschedule")
public class ClubScheduleController {
	@Autowired
	ClubScheduleRepository csRep;
	
	@Autowired
	ClubScheduleImageRepository csiRep;
	
	// 스케줄 입력 페이지 (날짜 선택 -> 해당 날짜 스케줄 입력)
	// 127.0.0.1:9090/ROOT/clubschedule/insert
	@GetMapping(value="/insert")
	public String insertGET(@RequestParam(name="sNo") long sNo)
	{
		try 
		{
			
			return "/3/clubschedule/insert";
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	//스케줄 입력
	@PostMapping(value="/insert")
	public String insertPOST(@ModelAttribute CSchedule cs, @RequestParam(name="file") MultipartFile[] file)
	{
		try 
		{
			csRep.save(cs);
			if(file != null)
			{
				if(file.length > 0)
				{
					for(int i=0; i<file.length; i++)
					{
						CsImage csImage = new CsImage();
						csImage.setCsiImage(file[i].getBytes());
						csImage.setCsiImagename(file[i].getOriginalFilename());
						csImage.setCsiImagesize(file[i].getSize());
						csImage.setCsiImagetype(file[i].getContentType());
						csImage.setCschedule(cs);
						csiRep.save(csImage);
					}
				}
			}
			
			return "redirect:/clubschedule/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 일정표 페이지 (게시판)
	// 127.0.0.1:9090/ROOT/clubschedule/selectlist
	@GetMapping(value="/selectlist")
	public String selectlistGET(Model model)
	{
		try 
		{
			List<CSchedule> list = csRep.findAll();
			model.addAttribute("list", list);
			
			return "/3/clubschedule/selectlist";
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
	}

}
