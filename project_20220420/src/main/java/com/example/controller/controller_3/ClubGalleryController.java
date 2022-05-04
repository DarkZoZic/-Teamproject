package com.example.controller.controller_3;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

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
	
	// 갤러리 생성 페이지
	// 127.0.0.1:9090/ROOT/clubgallery/insert
	@GetMapping(value="/insert")
	public String insertGet()
	{
		return "/3/clubgallery/insert";
	}
	
	// 갤러리 생성
	@PostMapping(value="/insert")
	public String insertPOST(@ModelAttribute ClubGallery cg, @RequestParam(name="file") MultipartFile[] file) throws IOException
	{
		try 
		{
			System.out.println("cg : " + cg);
			System.out.println(file.length);
			cgRep.save(cg);
			if(file != null) 
			{
				if(file.length > 0)// 이미지파일 첨부시
				{
					for(int i=0; i<file.length; i++)
					{
						GImage gImage = new GImage();
						System.out.println("file[i] : " + file[i].getContentType());
//						cg.setGThumbnail(file[0].getBytes());
						gImage.setGiImage(file[i].getBytes());
						gImage.setGiImagename(file[i].getOriginalFilename());
						gImage.setGiImagesize(file[i].getSize());
						gImage.setGiImagetype(file[i].getContentType());
						gImage.setClubgallery(cg);
						cgiRep.save(gImage);
//						System.out.println("gImage : " + gImage.getGiImagename().toString());
					}
					
					return "redirect:/clubgallery/selectlist";
				}
			}
			return "redirect:/clubgallery/insert"; // 미첨부시 등록 불가
//			System.out.println("getGThumbnailLength : " + cg.getGThumbnail().length);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 갤러리 목록
	// 127.0.0.1:9090/ROOT/clubgallery/selectlist
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
	
	// 갤러리 이미지 표시용
	// 127.0.0.1:9090/ROOT/clubgallery/image?gNo=&idx=
	@GetMapping(value="/image")
	public ResponseEntity<byte[]> imageGET(@RequestParam(name="gNo") long gNo, @RequestParam(name="idx") long idx) throws IOException
	{
		try
		{
			long imagecode = cgiRep.selectImageCode(gNo, idx);
//			System.out.println("imagecode = " + imagecode);
			GImage gImage = cgiRep.findById(imagecode).orElse(null);
			
//			System.out.println("size : " + gImage.getGiImagesize().toString());
//			System.out.println("length : " + gImage.getGiImage().length);
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
	
	// 갤러리 페이지
	// 127.0.0.1:9090/ROOT/clubgallery/select?gNo=
	@GetMapping(value="/select")
	public String selectGET(Model model, @RequestParam(name="gNo") long gNo)
	{
		try 
		{
			model.addAttribute("gallery", cgRep.findById(gNo).orElse(null));
			
			List<GImage> imagelist = cgiRep.findByClubgallery_gNoOrderByGiImgcodeAsc(gNo);
			model.addAttribute("imagelist", imagelist);
			
			
			return "/3/clubgallery/select"; 
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 갤러리 삭제
	// 127.0.0.1:9090/ROOT/clubgallery/delete
	@Transactional
	@PostMapping(value="/delete")
	public String deletePOST(@ModelAttribute ClubGallery cg)
	{
		try 
		{
//			System.out.println(cg.getGNo());
			cgiRep.deleteByClubgallery_gNo(cg.getGNo());
			cgRep.deleteById(cg.getGNo());
			
			return "redirect:/clubgallery/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 갤러리 수정 페이지
	// 127.0.0.1:9090/ROOT/clubgallery/update?gNo=
	@GetMapping(value="/update")
	public String updateGET(Model model, @RequestParam(name="gNo") long gNo)
	{
		try 
		{
//			System.out.println(gNo);
			model.addAttribute("gallery", cgRep.findById(gNo).orElse(null));
			
			List<GImage> imagelist = cgiRep.findByClubgallery_gNoOrderByGiImgcodeAsc(gNo);
			model.addAttribute("imagelist", imagelist);
			return "/3/clubgallery/update";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 갤러리 수정 페이지 - 선택한 이미지 일괄삭제
	// 127.0.0.1:9090/ROOT/clubgallery/deletegimages
	@PostMapping(value="/deletegimages")
	public String deletegimagesPOST(@ModelAttribute ClubGallery cg, @ModelAttribute GImage gi)
	{
		try 
		{
			System.out.println("cg : " + cg.toString());
			System.out.println(gi);
//			for(int i=0; i<gi.length; i++)
//			{
//				System.out.println("gi : " + gi[i].getGiImgcode());
//			}
			
			return "redirect:/clubgallery/update?gNo=" + cg.getGNo();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
}
