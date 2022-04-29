package com.example.controller.controller_3;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.entity1.ClubGallery;
import com.example.entity.entity1.GImage;
import com.example.repository.repository_3.ClubGalleryImageRepository;
import com.example.repository.repository_3.ClubGalleryRepository;

@Controller
@RequestMapping(value="/clubgallery")
public class ClubGalleryController {
	
	@Autowired
	ClubGalleryRepository cgRep;
	
	@Autowired
	ClubGalleryImageRepository cgiRep;
	
	@GetMapping(value="/insert")
	public String insertGet()
	{
		return "/3/clubgallery/insert";
	}
	
	@PostMapping(value="/insert")
	public String insertPOST(@ModelAttribute ClubGallery cg, @RequestParam(name="file") MultipartFile file) throws IOException
	{
		try 
		{
			GImage gImage = new GImage();
			
			cgRep.save(cg);
			
			if(file != null) 
			{
				if(!file.isEmpty())// 이미지파일 첨부시
				{
					gImage.setGiImage(file.getBytes());
					gImage.setGiImagename(file.getOriginalFilename());
					gImage.setGiImagesize(file.getSize());
					gImage.setGiImagetype(file.getContentType());
					cgiRep.save(gImage);
				}
			}
				
			return "redirect:/clubgallery/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
}
