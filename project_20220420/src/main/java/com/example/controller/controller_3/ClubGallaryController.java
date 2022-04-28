package com.example.controller.controller_3;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.entity1.ClubGallery;

@Controller
@RequestMapping(value="/clubgallery")
public class ClubGallaryController {
	
	@PostMapping(value="/insert")
	public String insertPOST(@ModelAttribute ClubGallery cg)
	{
		return null;
	}


}
