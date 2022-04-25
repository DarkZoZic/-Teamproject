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
	
	// http://127.0.0.1:9090/ROOT/clubgallery/insert
	@GetMapping(value="/insert")
	public String insertGET()
	{
		return "/clubgallery/insert";
	}
	
	@PostMapping(value="/insert")
	public String insertPOST(@ModelAttribute ClubGallery clubGallery)
	{
		try 
		{
			return "redirect:/";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}

}
