package com.example.controller.controller_3;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	// 127.0.0.1:9090/ROOT/clubgallery/selectlist?page=&text=
	@GetMapping(value="/selectlist")
	public String selectlistGET(Model model, @RequestParam(name="page", defaultValue="1") int page, 
			@RequestParam(name="text", defaultValue="") String text)
	{
		try
		{
			PageRequest pageRequest = PageRequest.of(page-1, 20); 
			System.out.println(pageRequest);
			
			List<ClubGallery> list = cgRep.findByCgNameContainingOrderByCgNoDesc(text, pageRequest);
			model.addAttribute("list", list);
			
			long total = cgRep.countByCgNameContaining(text);
			
			// pages = 1~20 = 1, 21~40 = 2, 41~60 = 3, ...... // 한 페이지에 20갤러리
			model.addAttribute("pages", (total-1) / 20 + 1);
			System.out.println("total = " + total);
			System.out.println((total-1) / 20 + 1);
			return "/3/clubgallery/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	
	
	// 갤러리 이미지 표시용
	// 127.0.0.1:9090/ROOT/clubgallery/image?cgNo=&idx=
	@GetMapping(value="/image")
	public ResponseEntity<byte[]> imageGET(@RequestParam(name="cgNo") long cgNo, @RequestParam(name="idx") long idx) throws IOException
	{
		try
		{
			long imagecode = cgiRep.selectImageCode(cgNo, idx);
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
	// 127.0.0.1:9090/ROOT/clubgallery/select?cgNo=
	@GetMapping(value="/select")
	public String selectGET(Model model, @RequestParam(name="cgNo") long cgNo)
	{
		try 
		{
			model.addAttribute("gallery", cgRep.findById(cgNo).orElse(null));
			
			List<GImage> imagelist = cgiRep.findByClubgallery_cgNoOrderByGiImgcodeAsc(cgNo);
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
			cgiRep.deleteByClubgallery_cgNo(cg.getCgNo());
			cgRep.deleteById(cg.getCgNo());
			
			return "redirect:/clubgallery/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 갤러리 수정 페이지
	// 127.0.0.1:9090/ROOT/clubgallery/update?cgNo=
	@GetMapping(value="/update")
	public String updateGET(Model model, @RequestParam(name="cgNo") long cgNo)
	{
		try 
		{
//			System.out.println(cgNo);
			model.addAttribute("gallery", cgRep.findById(cgNo).orElse(null));
			
			List<GImage> imagelist = cgiRep.findByClubgallery_cgNoOrderByGiImgcodeAsc(cgNo);
			model.addAttribute("imagelist", imagelist);
			return "/3/clubgallery/update";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 갤러리명, 갤러리설명 수정
	// 127.0.0.1:9090/ROOT/clubgallery/updategallerytext
	@PostMapping(value="/updategallerytext")
	public String updategalleryPOST(@ModelAttribute ClubGallery cg)
	{
		try 
		{
			ClubGallery gregdate = cgRep.findById(cg.getCgNo()).orElse(null);
//			System.out.println("gregdate : " + gregdate.getGRegdate());
			
//			System.out.println("cg : " + cg);
			cg.setCgRegdate(gregdate.getCgRegdate());
			cgRep.save(cg);
			return "redirect:/clubgallery/update?cgNo=" + cg.getCgNo();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 갤러리 수정 페이지 - 이미지 일괄등록
	// 127.0.0.1:9090/ROOT/clubgallery/insertgimages
	@PostMapping(value="/insertimages")
	public String insertimagesPOST(@ModelAttribute ClubGallery cg, @RequestParam(name="file") MultipartFile[] file) throws IOException
	{
		try 
		{
//			System.out.println("cg : " + cg);
//			System.out.println("file.length : " + file.length);
			if(file != null) 
			{
				if(file.length > 0)// 이미지파일 첨부시
				{
					for(int i=0; i<file.length; i++)
					{
						GImage gImage = new GImage();
						System.out.println("file[i] : " + file[i].getContentType());
						gImage.setGiImage(file[i].getBytes());
						gImage.setGiImagename(file[i].getOriginalFilename());
						gImage.setGiImagesize(file[i].getSize());
						gImage.setGiImagetype(file[i].getContentType());
						gImage.setClubgallery(cg);
						cgiRep.save(gImage);
//							System.out.println("gImage : " + gImage.getGiImagename().toString());
					}
					return "redirect:/clubgallery/update?cgNo=" + cg.getCgNo();
				}
			}
			return "redirect:/clubgallery/update?cgNo=" + cg.getCgNo();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 갤러리 수정 페이지 - 이미지 삭제
	// 127.0.0.1:9090/ROOT/clubgallery/deletegimage
	@PostMapping(value="/deletegimage")
	public String deletegimagesPOST(@ModelAttribute ClubGallery cg, @ModelAttribute GImage gi)
	{
		try 
		{
//			System.out.println("//////////////////////////////////////////");
//			System.out.println("cg : " + cg);
//			System.out.println("gi : " + gi);
//			System.out.println("//////////////////////////////////////////");
			
			cgiRep.deleteById(gi.getGiImgcode());
			
			return "redirect:/clubgallery/update?cgNo=" + cg.getCgNo();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
}
