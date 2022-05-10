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
import com.example.entity.entity2.CaImage;
import com.example.entity.entity2.ClubAlbum;
import com.example.repository.repository_3.ClubAlbumImageRepository;
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
	ClubAlbumImageRepository caiRep;
	
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
	// 127.0.0.1:9090/ROOT/clubalbum/image?cano=&idx=
	@GetMapping(value="/image")
	public ResponseEntity<byte[]> imageGET(@RequestParam(name="cano") long cano, @RequestParam(name="idx") long idx) throws IOException
	{
		try
		{
			long imagecode = caiRep.selectAlbumImageCode(cano, idx);
		
			System.out.println("imagecode = " + imagecode);
			
			CaImage caImage = caiRep.findById(imagecode).orElse(null);
			
			HttpHeaders headers = new HttpHeaders();
			if(caImage.getCaimagesize() > 0)
			{
				if(caImage.getCaimagetype().equals("image/jpeg"))
				{
					headers.setContentType(MediaType.IMAGE_JPEG);
				}
				else if(caImage.getCaimagetype().equals("image/png"))
				{
					headers.setContentType(MediaType.IMAGE_PNG);
				}
				else if(caImage.getCaimagetype().equals("image/gif"))
				{
					headers.setContentType(MediaType.IMAGE_GIF);
				}
				ResponseEntity<byte[]> response = new ResponseEntity<>(caImage.getCaimage(), headers, HttpStatus.OK);
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
	// 127.0.0.1:9090/ROOT/clubalbum/select?cano=
	@GetMapping(value="/select")
	public String selectGET(Model model, @RequestParam(name="cano") long cano)
	{
		try 
		{
			model.addAttribute("album", caRep.findById(cano).orElse(null));
			
			List<CaImage> imagelist = caiRep.findByClubalbum_canoOrderByCaimgcodeAsc(cano);
			System.out.println("imagelist : " + imagelist.toString());
			model.addAttribute("imagelist", imagelist);
						
			return "/3/clubalbum/select"; 
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 앨범에 선택한 갤러리 이미지 추가
	// 127.0.0.1:9090/ROOT/clubalbum/insertimage?cano=
	@PostMapping(value="/insertimage")
	public String insertimagePOST(@RequestParam(name="cano") ClubAlbum cano, @ModelAttribute GImage gimage)
	{
		try 
		{
			System.out.println(cano);
			System.out.println(gimage);
//			System.out.println(cgiRep.findById(gimage.getGimgcode()).orElse(null));
			
			// gimage = 이미지코드만 가지고 있는 모델 // 이미지코드가 일치하는 이미지파일 데이터 가져와서 image 변수에 넣기
			GImage image = cgiRep.findById(gimage.getGimgcode()).orElse(null); 
			// GImage -> CaImage 변환용 변수
			CaImage caImage = new CaImage();
			// 중복조회용 gimgcode
			caImage.setGimage(gimage);
			
			// 앨범에 추가하려는 이미지 코드 = 
			// gimgcode와 cano가 일치하는 이미지가 없으면(이미지가 해당 앨범 내에서 중복이 아니면)
			if(caiRep.findByGimage_gimgcode(caImage.getGimage().getGimgcode()) == null)
			{
				// image변수에 넣은 이미지데이터 caImage 모델에 넣기
				caImage.setCaimage(image.getGimage());
				caImage.setCaimagename(image.getGimagename());
				caImage.setCaimagesize(image.getGimagesize());
				caImage.setCaimagetype(image.getGimagetype());
				caImage.setClubalbum(cano);
				// CaImage 테이블에 이미지 저장
				caiRep.save(caImage);
			}
			
			return "redirect:/clubalbum/select?cano=" + cano.getCano();
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
	public String gallerylistGET(Model model, @RequestParam(name="cano") long cano)
	{
		try 
		{
			model.addAttribute("list", cgRep.findAll());
			model.addAttribute("cano", cano);
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
	public String imagelistGET(Model model, @RequestParam(name="cgno") long cgno, @RequestParam(name="cano") long cano)
	{
		try 
		{
			model.addAttribute("imagelist", cgiRep.findByClubgallery_cgnoOrderByGimgcodeAsc(cgno));
			model.addAttribute("cano", cano);
			return "/3/clubalbum/insertimages/imagelist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 앨범 삭제(갤러리 이미지 - 앨범 연동 끊는 기능 미구현)
	// 127.0.0.1:9090/ROOT/clubalbum/delete
	@PostMapping(value="/delete")
	public String deleteGET(@ModelAttribute ClubAlbum ca)
	{
		try 
		{
			caRep.deleteById(ca.getCano());
			return "redirect:/clubalbum/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
}
