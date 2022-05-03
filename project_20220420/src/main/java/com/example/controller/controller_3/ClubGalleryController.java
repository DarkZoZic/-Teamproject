package com.example.controller.controller_3;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Autowired
	ResourceLoader resLoader;
	
	//127.0.0.1:9090/ROOT/clubgallery/insert
	@GetMapping(value="/insert")
	public String insertGet()
	{
		return "/3/clubgallery/insert";
	}
	
	@PostMapping(value="/insert")
	public String insertPOST(@ModelAttribute ClubGallery cg, @RequestParam(name="file", required = false) MultipartFile[] file) throws IOException
	{
		try 
		{
			GImage gImage = new GImage();
			System.out.println("cg : " + cg);
			if(file != null) 
			{
				if(file.length > 0)// 이미지파일 첨부시
				{
					for(int i=0; i<file.length; i++)
					{
						cg.setGThumbnail(file[0].getBytes());
						gImage.setGiImage(file[i].getBytes());
						gImage.setGiImagename(file[i].getOriginalFilename());
						gImage.setGiImagesize(file[i].getSize());
						gImage.setGiImagetype(file[i].getContentType());
						cgiRep.save(gImage);
					}
				}
			}
			System.out.println("getGThumbnailLength : " + cg.getGThumbnail().length);
			cgRep.save(cg);
			
			return "redirect:/clubgallery/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	//127.0.0.1:9090/ROOT/clubgallery/selectlist
	@GetMapping(value="/selectlist")
	public String selectlistGET(Model model)
	{
		try
		{
			List<ClubGallery> list = cgRep.findAll();
			model.addAttribute("list", list);
			
			return "/3/clubgallery/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 127.0.0.1:9090/ROOT/clubgallery/image?gNo=&giImgcode=
	@GetMapping(value="/image")
	public ResponseEntity<byte[]> imageGET(@RequestParam(name="gNo") long gNo, @RequestParam(name="giImgcode") long giImgcode) throws IOException
	{
		try
		{
			GImage gImage = cgiRep.selectImage(gNo, giImgcode);
			System.out.println("size : " + gImage.getGiImagesize().toString());
			System.out.println("length : " + gImage.getGiImage().length);
			HttpHeaders headers = new HttpHeaders();
			if(gImage.getGiImagesize() > 0)
			{
				if(gImage.getGiImagetype().equals("image/jpeg")) {
					headers.setContentType(MediaType.IMAGE_JPEG);
				}
				else if(gImage.getGiImagetype().equals("image/png")) {
					headers.setContentType(MediaType.IMAGE_PNG);
				}
				else if(gImage.getGiImagetype().equals("image/gif")) {
					headers.setContentType(MediaType.IMAGE_GIF);
				}
				ResponseEntity<byte[]> response = new ResponseEntity<>(gImage.getGiImage(), headers, HttpStatus.OK);
				return response;
			}
			else
			{
				InputStream is = resLoader.getResource("classpath:/static/images/default.png").getInputStream();
				headers.setContentType(MediaType.IMAGE_PNG);
				
				ResponseEntity<byte[]> response = new ResponseEntity<>(is.readAllBytes(), headers, HttpStatus.OK );
				return response;
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/select")
	public String selectGET(Model model, @RequestParam(name="gNo") long gNo)
	{
		try 
		{
			model.addAttribute("gallery", cgRep.findById(gNo)); 
			//clubgallery, gimage entity 수정
			
			return "/3/clubgallery/select?gNo=" + gNo; 
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}
}
