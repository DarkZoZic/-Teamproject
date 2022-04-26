package com.example.controller.controller_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.repository_3.ClubGallaryRepository;
import com.example.entity.entity1.ClubGallery;

@Controller
@RequestMapping(value="/clubgallery")
public class ClubGallaryController {
	@Autowired
	ClubGallaryRepository cgRep;
	
	// 크럽갤러리 글작성 페이지
	// http://127.0.0.1:9090/ROOT/clubgallery/insert
	@GetMapping(value="/insert")
	public String insertGET()
	{
		return "/clubgallery/insert";
	}
	
	// 클럽갤러리 글작성
	// http://127.0.0.1:9090/ROOT/clubgallery/insertact
	@PostMapping(value="/insertact")
	public String insertPOST(@ModelAttribute ClubGallery clubGallery)
	{
		try 
		{
			cgRep.save(clubGallery);
			return "redirect:/clubgallery/selectlist";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}

}
