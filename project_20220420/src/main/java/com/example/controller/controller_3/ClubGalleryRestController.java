package com.example.controller.controller_3;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.example.entity.entity2.CReply;
import com.example.entity.entity2.Club;
import com.example.repository.repository_3.CReplyRepository;
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
	CReplyRepository crRep;
	
	@Autowired
	ResourceLoader resLoader;
	
	// 클럽갤러리 생성
	// /ROOT/api/clubgallery/insert
	@RequestMapping(value="/insert", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertPOST(@ModelAttribute ClubGallery cg, @ModelAttribute MultipartFile[] file, @RequestParam(name="cno") Club cno) throws IOException
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			cg.setClub(cno);
			System.out.println("cg : " + cg);
			System.out.println(file.length);
			
			if(file != null) 
			{
				if(file.length > 0)// 이미지파일 첨부시
				{
					cgRep.save(cg);
					for(int i=0; i<file.length; i++)
					{
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
	// /ROOT/api/clubgallery/selectlist?page=&text=&cno=
	@RequestMapping(value="/selectlist", 
			method={RequestMethod.GET}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> selectlistGET(Model model, @RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="text", defaultValue="") String text,
			@RequestParam(name="cno") long cno)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			//1페이지 당 20글 표시
			PageRequest pageRequest = PageRequest.of(page-1, 20); 
			System.out.println(pageRequest);
			
			//검색어 포함, 1페이지 20글, 글번호 내림차순
			List<ClubGallery> list = cgRep.findByCgnameAndClub_cnoContainingOrderByCgnoDesc(text, cno, pageRequest);
			
			model.addAttribute("list", list);
			System.out.println("list : " + list);
			
			for(int i=0; i<list.toArray().length; i++)
			{
				ClubGallery cg = list.get(i);
//				System.out.println("cg : " + cg.getCgno());
				
				ClubGallery clubGallery = cgRep.findById(cg.getCgno()).orElse(null);
				
				clubGallery.setGimageurl("/ROOT/clubgallery/image?cgno=" + cg.getCgno() + "&idx=0");
			}
			
			//페이지네이션 구현용 글 개수 가져와서 model에 넣기
			long total = cgRep.countByCgnameAndClub_cnoContaining(text, cno);
			
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
	// 갤러리 상세보기 + 댓글목록 + 이미지 url
	// /ROOT/api/clubgallery/select?cgno=
	@RequestMapping(value="/select", 
			method={RequestMethod.GET}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertPOST(Model model, @RequestParam(name="cgno") long cgno)
	{
		Map<String, Object> map = new HashMap<>();
		try {
			
			ClubGallery clubGallery = cgRep.findById(cgno).orElse(null);
			long imagecount = cgiRep.countByClubgallery_cgno(cgno);
			
			clubGallery.setGimageurl("/ROOT/clubgallery/image?cgno=" + cgno); // 이미지 url 보내기(idx값은 프론트에서 반복문으로 입력)
			// 댓글 목록 저장할 배열 변수
			List<CReply> replylist = crRep.findByClubgallery_cgnoOrderByRenumberDesc(cgno);
			
			model.addAttribute("clubgallery", clubGallery); //글상세내용(이미지 url 포함)		
			model.addAttribute("replylist", replylist); // 댓글
			model.addAttribute("imagecount", imagecount); // 이미지 개수(idx)
			
//				System.out.println("image : " + image);
//				if(image != null) // 글에 첨부된 이미지가 있으면
//				{
//			model.addAttribute("cbimage", image); //이미지
//				}
			
			map.put("status", 200);
			map.put("result", model);
		} 
		catch (Exception e) 
		{
			map.put("status", 0);
		}
		return map;
	}
	
	// 갤러리 이미지 표시
	// /ROOT/api/clubgallery/image/cgno=&idx=
//	@RequestMapping(value="/image", 
//			method={RequestMethod.GET}, 
//			consumes = {MediaType.ALL_VALUE},
//			produces= {MediaType.APPLICATION_JSON_VALUE})
//	public Map<String, Object> imageGET(@RequestParam(name="cgno") long cgno, @RequestParam(name="idx") long idx) throws IOException
//	{
//		Map<String, Object> map = new HashMap<>();
//		try 
//		{
//			// cgno(갤러리번호) 조회해서 gimgcode 찾기
//			long imagecode = cgiRep.selectImageCode(cgno, idx);
//			
//			// 찾은 giImgcode와 일치하는(해당 갤러리에 등록한) 이미지 찾기
//			GImage gImage = cgiRep.findById(imagecode).orElse(null);
//			
//			System.out.println("gimage : " + gImage.getGimagename());
//			
//			map.put("status", 200);
//			map.put("result", gImage);
//		}
//		catch (Exception e) 
//		{
//			map.put("status", 0);
//		}
//		return map;
//	}
	
	
	
	// 갤러리 삭제
	// /ROOT/api/clubgallery/delete
	@Transactional
	@RequestMapping(value="/delete", 
	method={RequestMethod.DELETE}, 
	consumes = {MediaType.ALL_VALUE},
	produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> delete(@RequestBody ClubGallery cg)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			cgiRep.deleteByClubgallery_cgno(cg.getCgno());
			cgRep.deleteById(cg.getCgno());
			map.put("status", 200);
		} 
		catch (Exception e) 
		{
			map.put("status", 0);
		}
		return map;
	}
	
	// 클럽갤러리 댓글쓰기
	// /ROOT/api/clubgallery/insertreply?cgno=
	@RequestMapping(value="/insertreply", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertreplyPOST(@RequestBody CReply cr, @RequestParam(name="cgno") long cgno)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			ClubGallery cg = cgRep.findById(cgno).orElse(null);
			cr.setClubgallery(cg); // 댓글 작성한 갤러리 번호 저장
			crRep.save(cr);
			map.put("status", 200);
		} 
		catch (Exception e) 
		{
			map.put("status", 0);
		}
		return map;
	}
	
	// 클럽갤러리 댓글삭제
	// /ROOT/api/clubgallery/deletereply
	@RequestMapping(value="/deletereply", 
			method={RequestMethod.DELETE}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> deletereplyDELETE(@RequestBody CReply cr)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			crRep.deleteById(cr.getRenumber());
			map.put("status", 200);
		}
		catch (Exception e)
		{
			map.put("status", 0);
		}
		return map;
	}
	
}
