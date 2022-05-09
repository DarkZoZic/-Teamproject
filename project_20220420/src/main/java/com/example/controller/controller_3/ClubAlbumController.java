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

import com.example.entity.entity1.GImage;
import com.example.entity.entity2.ClubAlbum;
import com.example.repository.repository_3.ClubAlbumRepository;
import com.example.repository.repository_3.ClubGalleryImageRepository;
import com.example.repository.repository_3.ClubGalleryRepository;

@Controller
@RequestMapping(value="/clubalbum")
public class ClubAlbumController {
	@Autowired
	ClubAlbumRepository caRep;
	
	@Autowired
	ClubGalleryRepository cgRep;
	
	@Autowired
	ClubGalleryImageRepository cgiRep;
	
	@Autowired
	ResourceLoader resLoader;
	
	// 새 앨범 생성 페이지
	// 127.0.0.1:9090/ROOT/clubalbum/insert
	@GetMapping(value="/insert")
	public String insertGET()
	{
		return "/3/clubalbum/insert";
	}
	
	//새 앨범 생성
	@PostMapping(value="/insert")
	public String insertPOST(@ModelAttribute ClubAlbum ca)
	{
		try 
		{
			System.out.println(ca);
			caRep.save(ca);
			return "redirect:/clubalbum/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 생성한 앨범 목록 페이지
	// 127.0.0.1:9090/ROOT/clubalbum/selectlist
	@GetMapping(value="/selectlist")
	public String selectGET(Model model)
	{
		try 
		{
			List<ClubAlbum> list = caRep.findAll();
			model.addAttribute("list", list);
			return "/3/clubalbum/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 앨범 이미지 표시용
	// 127.0.0.1:9090/ROOT/clubalbum/image?caNo=&idx=
	@GetMapping(value="/image")
	public ResponseEntity<byte[]> imageGET(@RequestParam(name="caNo") long caNo, @RequestParam(name="idx") long idx) throws IOException
	{
		try
		{
			// caNo(앨범번호) 조회해서 giImgcode 찾기
			long imagecode = cgiRep.selectAlbumImageCode(caNo, idx);
			
			// 찾은 giImgcode와 일치하는(해당 앨범에 등록한) 이미지 전부 찾기
			GImage gImage = cgiRep.findById(imagecode).orElse(null);
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
	
	// 앨범 상세화면
	// 127.0.0.1:9090/ROOT/clubalbum/select?caNo=
	@GetMapping(value="/select")
	public String selectGET(Model model, @RequestParam(name="caNo") long caNo)
	{
		try 
		{
			model.addAttribute("album", caRep.findById(caNo).orElse(null));
			
			List<GImage> imagelist = cgiRep.findByClubalbum_caNoOrderByGiImgcodeAsc(caNo);
			model.addAttribute("imagelist", imagelist);
						
			return "/3/clubalbum/select"; 
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 앨범에 선택한 갤러리 이미지 추가 // 미구현
	// 127.0.0.1:9090/ROOT/clubalbum/insertimage?caNo=
	@PostMapping(value="/insertimage")
	public String insertimagePOST(@RequestParam(name="caNo") long caNo, @ModelAttribute GImage gimage)
	{
		try 
		{
			System.out.println(caNo);
			System.out.println(gimage.toString());
//			cgiRep.insertImageInAlbum(caNo, gimage.getGiImgcode());
			return "redirect:/clubalbum/select";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 앨범에 이미지 추가 기능 사용 시 갤러리 목록 표시용
	// 127.0.0.1:9090/ROOT/clubalbum/gallerylist
	@GetMapping(value="/gallerylist")
	public String gallerylistGET(Model model, @RequestParam(name="caNo") long caNo)
	{
		try 
		{
			model.addAttribute("list", cgRep.findAll());
			model.addAttribute("caNo", caNo);
			return "/3/clubalbum/insertimages/gallerylist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 앨범에 이미지 추가 기능 사용 시 갤러리 목록 -> 선택한 갤러리 이미지 목록 표시용
	// 127.0.0.1:9090/ROOT/clubalbum/imagelist
	@GetMapping(value="/imagelist")
	public String imagelistGET(Model model, @RequestParam(name="cgNo") long cgNo, @RequestParam(name="caNo") long caNo)
	{
		try 
		{
			model.addAttribute("imagelist", cgiRep.findByClubgallery_cgNoOrderByGiImgcodeAsc(cgNo));
			model.addAttribute("caNo", caNo);
			return "/3/clubalbum/insertimages/imagelist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
}
