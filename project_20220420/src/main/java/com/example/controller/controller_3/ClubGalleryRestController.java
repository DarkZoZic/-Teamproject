package com.example.controller.controller_3;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.entity1.ClubGallery;
import com.example.entity.entity1.GImage;
import com.example.repository.repository_3.ClubGalleryImageRepository;
import com.example.repository.repository_3.ClubGalleryRepository;

@RestController
@RequestMapping(value="/api/clubgallery")
public class ClubGalleryRestController {
	@Autowired
	ClubGalleryRepository cgRep;
	
	@Autowired
	ClubGalleryImageRepository cgiRep;
	
	@Autowired
	ResourceLoader resLoader;
	
	// 클럽갤러리 생성
	// /ROOT/api/clubgallery/insert
	@RequestMapping(value="/insert", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertPOST(@RequestBody ClubGallery cg, @ModelAttribute MultipartFile[] file) throws IOException
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			System.out.println("cg : " + cg);
			System.out.println(file.length);
			
			if(file != null) 
			{
				if(file.length > 0)// 이미지파일 첨부시
				{
					for(int i=0; i<file.length; i++)
					{
						cgRep.save(cg);
						GImage gImage = new GImage();
						System.out.println("file[i] : " + file[i].getContentType());
//						cg.setGThumbnail(file[0].getBytes());
						gImage.setGimage(file[i].getBytes());
						gImage.setGimagename(file[i].getOriginalFilename());
						gImage.setGimagesize(file[i].getSize());
						gImage.setGimagetype(file[i].getContentType());
						gImage.setClubgallery(cg);
						cgiRep.save(gImage);
						map.put("status", 200);
					}	
				}
			}
		} 
		catch (Exception e) 
		{
			map.put("status", 0);
		}
		return map;
	}
	
	// 클럽갤러리 목록
	// /ROOT/api/clubgallery/selectlist
	@RequestMapping(value="/selectlist", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> selectlistPOST(Model model, @RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="text", defaultValue="") String text)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			//1페이지 당 20글 표시
			PageRequest pageRequest = PageRequest.of(page-1, 20); 
			System.out.println(pageRequest);
			
			//검색어 포함, 1페이지 20글, 글번호 내림차순
			List<ClubGallery> list = cgRep.findByCgnameContainingOrderByCgnoDesc(text, pageRequest);
			model.addAttribute("list", list);
//			System.out.println(list.toString());
			
			//페이지네이션 구현용 글 개수 가져와서 model에 넣기
			long total = cgRep.countByCgnameContaining(text);
			
			// pages = 1~20 = 1, 21~40 = 2, 41~60 = 3, ...... // 한 페이지에 20글
			model.addAttribute("pages", (total-1) / 20 + 1);
			System.out.println("total = " + total);
			System.out.println((total-1) / 20 + 1);
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
