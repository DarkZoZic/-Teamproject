package com.example.controller.controller_3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.entity1.ClubGallery;
import com.example.entity.entity1.GImage;
import com.example.entity.entity1.Member;
import com.example.entity.entity2.CReply;
import com.example.entity.entity2.Club;
import com.example.entity.entity2.ClubBoard;
import com.example.jwt.JwtUtil;
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
	
	@Autowired
	JwtUtil jwtUtil;
	
	// 클럽갤러리 생성
	// /ROOT/api/clubgallery/insert
	@RequestMapping(value="/insert", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertPOST(@ModelAttribute ClubGallery cg, @ModelAttribute MultipartFile[] file,
			@RequestHeader(name="token") String token) throws IOException
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				System.out.println("cg : " + cg);
				System.out.println(file.length);
				
				Member mid = new Member(); 
				mid.setMid(jwtUtil.extractUsername(token));
				cg.setMember(mid);
				
				if(file != null) 
				{
					if(file.length > 0)// 이미지파일 첨부시
					{
						cgRep.save(cg);
						for(int i=0; i<file.length; i++)
						{
							GImage gImage = new GImage();
							System.out.println("file[i] : " + file[i].getContentType());
//							cg.setGThumbnail(file[0].getBytes());
							gImage.setGimage(file[i].getBytes());
							gImage.setGimagename(file[i].getOriginalFilename());
							gImage.setGimagesize(file[i].getSize());
							gImage.setGimagetype(file[i].getContentType());
							gImage.setClubgallery(cg);
							cgiRep.save(gImage);
							map.put("status", 200);
						}	
					}
					else
					{
						map.put("status", 0);
						map.put("result", "이미지없음");
					}
				}
			}
			else
			{
				map.put("status", 0);
				map.put("result", "토큰없음");
			}
		} 
		catch (Exception e) 
		{
			map.put("status", -1);
		}
		return map;
	}
	
	// 클럽갤러리 목록
	// /ROOT/api/clubgallery/selectlist?page=&text=&option=&cno=
	@RequestMapping(value="/selectlist", 
			method={RequestMethod.GET}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> selectlistGET(Model model, 
			@RequestParam(name="page", defaultValue="1") int page, 
			@RequestParam(name="text", defaultValue="", required=false) String text,
			@RequestParam(name="option", defaultValue="", required=false) String option,
			@RequestParam(name="cno") long cno, 
			@RequestHeader(name="token") String token)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				//1페이지 당 10글 표시
				PageRequest pageRequest = PageRequest.of(page-1, 10); 
				System.out.println(pageRequest);
				
				//검색어 포함, 1페이지 10글, 글번호 내림차순
				List<ClubGallery> list = new ArrayList<>();
				if(option.equals("갤러리명"))
				{
					list = cgRep.findByCgnameContainingAndClub_cnoOrderByCgnoDesc(text, cno, pageRequest);
				}
				else if(option.equals("갤러리설명"))
				{
					list = cgRep.findByCgdescContainingAndClub_cnoOrderByCgnoDesc(text, cno, pageRequest);
				}
				else if(option.equals("갤러리작성자"))
				{
					list = cgRep.findByMember_midContainingAndClub_cnoOrderByCgnoDesc(text, cno, pageRequest);
				}
//				else if(option.equals("전체"))
//				{
//					list = cgRep.findByAllOptions(text, pageRequest);
//				}
				else
				{
					list = cgRep.findByClub_cnoOrderByCgnoDesc(cno, pageRequest);
				}
				
//				System.out.println("list : " + list);
				
				//페이지 구현용 글 개수
				long total = list.toArray().length;
//				System.out.println("total = " + total);
				model.addAttribute("total", total);
				
				// pages = 1~10 = 1, 11~20 = 2, 21~30 = 3, ...... // 한 페이지에 10글
//				System.out.println("pages : " + (total-1) / 10 + 1);
				model.addAttribute("pages", (total-1) / 10 + 1);	
				
				for(int i=0; i<list.toArray().length; i++) //list 내 모든 갤러리에 각각의 썸네일 주소를 부여 
				{
					ClubGallery cg = list.get(i);
					
					ClubGallery clubGallery = cgRep.findById(cg.getCgno()).orElse(null);
					
					clubGallery.setGimageurl("/ROOT/clubgallery/image?cgno=" + cg.getCgno() + "&idx=0");
				}
				
				model.addAttribute("list", list);
				
				map.put("status", 200);
				map.put("result", model);
			}
			else
			{
				map.put("status", 0);
				map.put("result", "토큰없음");
			}
			
		} 
		catch (Exception e) 
		{
			map.put("status", -1);
		}
		return map;
	}
	// 갤러리 상세보기 + 댓글목록 + 이미지 url
	// /ROOT/api/clubgallery/select?cgno=
	@RequestMapping(value="/select", 
			method={RequestMethod.GET}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertPOST(Model model, @RequestParam(name="cgno") long cgno, @RequestParam(name="cno") long cno, @RequestHeader(name="token") String token)
	{
		Map<String, Object> map = new HashMap<>();
		try {
			if(token != null)
			{
				ClubGallery clubGallery = cgRep.findById(cgno).orElse(null);
				long imagecount = cgiRep.countByClubgallery_cgno(cgno); // idx값
				clubGallery.setGimageurl("/ROOT/clubgallery/image?cgno=" + cgno); // 이미지 url 보내기(idx값은 프론트에서 반복문으로 입력)
				
				// 댓글 목록 저장할 배열 변수
				List<CReply> replylist = crRep.findByClubgallery_cgnoOrderByRenumberDesc(cgno);
				
				System.out.println(cno);
				
				model.addAttribute("clubgallery", clubGallery); //글상세내용(이미지 url 포함)		
				model.addAttribute("replylist", replylist); // 댓글
				model.addAttribute("imagecount", imagecount); // 이미지 개수(idx)
				
//					System.out.println("image : " + image);
//					if(image != null) // 글에 첨부된 이미지가 있으면
//					{
//				model.addAttribute("cbimage", image); //이미지
//					}
				
				map.put("status", 200);
				map.put("result", model);
			}
			else
			{
				map.put("status", 0);
				map.put("result", "토큰없음");
			}
		} 
		catch (Exception e) 
		{
			map.put("status", -1);
		}
		return map;
	}
	
	// 갤러리 조회수 증가
	@RequestMapping(value="/updatehit", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> clubgalleryUpdatehit1PUT(@RequestParam(name="cgno") long cgno, @RequestParam(name="cno") long cno, @RequestHeader(name="token") String token)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				ClubGallery gallery = cgRep.findById(cgno).orElse(null);
				Club cnum = new Club();
				cnum.setCno(cno);
				gallery.setClub(cnum);
				gallery.setCghit( gallery.getCghit() + 1L );
				System.out.println(gallery.toString());
				cgRep.save(gallery);
				map.put("status", 200);
			}
			else
			{
				map.put("status", 0);
				map.put("result", "토큰없음");
			}
			
		} 
		catch (Exception e) 
		{
			map.put("status", -1);
		}
		return map;
	}
	
	
	
	// 갤러리 삭제
	// /ROOT/api/clubgallery/delete
	@Transactional
	@RequestMapping(value="/delete", 
	method={RequestMethod.POST}, 
	consumes = {MediaType.ALL_VALUE},
	produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> delete(@RequestBody ClubGallery cg, @RequestHeader(name="token") String token)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				cgiRep.deleteByClubgallery_cgno(cg.getCgno());
				cgRep.deleteById(cg.getCgno());
				map.put("status", 200);
			}
			else
			{
				map.put("status", 0);
				map.put("result", "토큰없음");
			}
			
		} 
		catch (Exception e) 
		{
			map.put("status", -1);
		}
		return map;
	}
	
	// 클럽갤러리 댓글쓰기
	// /ROOT/api/clubgallery/insertreply?cgno=
	@RequestMapping(value="/insertreply", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertreplyPOST(@RequestBody CReply cr, @RequestParam(name="cgno") long cgno, @RequestHeader(name="token") String token)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				ClubGallery cg = cgRep.findById(cgno).orElse(null);
				cr.setClubgallery(cg); // 댓글 작성한 갤러리 번호 저장
				crRep.save(cr);
				map.put("status", 200);
			}
			else
			{
				map.put("status", 0);
				map.put("result", "토큰없음");
			}
			
		} 
		catch (Exception e) 
		{
			map.put("status", -1);
		}
		return map;
	}
	
	// 클럽갤러리 댓글삭제
	// /ROOT/api/clubgallery/deletereply
	@RequestMapping(value="/deletereply", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> deletereplyDELETE(@RequestBody CReply cr, @RequestHeader(name="token") String token)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				crRep.deleteById(cr.getRenumber());
				map.put("status", 200);
			}
			else
			{
				map.put("status", 0);
				map.put("result", "토큰없음");
			}
		}
		catch (Exception e)
		{
			map.put("status", 0);
		}
		return map;
	}
	
}
