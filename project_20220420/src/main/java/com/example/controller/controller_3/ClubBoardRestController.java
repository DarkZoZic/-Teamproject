package com.example.controller.controller_3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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

import com.example.entity.entity1.Member;
import com.example.entity.entity1.MemberPersonal;
import com.example.entity.entity2.CReply;
import com.example.entity.entity2.CbImage;
import com.example.entity.entity2.Cbckeditor;
import com.example.entity.entity2.Club;
import com.example.entity.entity2.ClubBoard;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_3.CReplyRepository;
import com.example.repository.repository_3.CbckeditorRepository;
import com.example.repository.repository_3.ClubBoardImageRepository;
import com.example.repository.repository_3.ClubBoardRepository;
import com.example.repository.repository_3.PersonalMemberRepository;
import com.example.repository.repository_gibum.ClubRepository;

@RestController
@RequestMapping(value="/api/clubboard")
public class ClubBoardRestController {
	@Autowired
	ClubBoardRepository cbRep;
	
	@Autowired
	ClubBoardImageRepository cbiRep;
	
	@Autowired
	CReplyRepository crRep;
	
	@Autowired
	CbckeditorRepository cbckRep;
	
	@Autowired
	ResourceLoader resLoader;
	
	@Autowired
	ClubRepository cRep;
	
	@Autowired
	PersonalMemberRepository pmRep;
	
	@Autowired
	JwtUtil jwtUtil;
	
	// 클럽게시판 글작성
	// /ROOT/api/clubboard/insert
	@RequestMapping(value="/insert", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertPOST(@ModelAttribute ClubBoard cb, @ModelAttribute MultipartFile file,
			@RequestHeader(name="token") String token) throws IOException
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				System.out.println("cno : " + cb.getClub().getCno());
				Member mid = new Member(); 
				mid.setMid(jwtUtil.extractUsername(token));
				cb.setMember(mid);
				
				System.out.println("cb : " + cb);
//				System.out.println(file.getOriginalFilename());
				
				
				cbRep.save(cb);
				if(file != null)
				{
					if(!file.isEmpty()) // 이미지 첨부시
					{
						CbImage cbImage = new CbImage(); // 첨부한 이미지파일 받을 변수
						cbImage.setCbimage(file.getBytes()); 
						cbImage.setCbimagename(file.getOriginalFilename());
						cbImage.setCbimagesize(file.getSize());
						cbImage.setCbimagetype(file.getContentType());
						cbImage.setClubboard(cb);
						cbiRep.save(cbImage); // 이미지파일 변수에 이미지 데이터 넣고 CbImage 테이블에 저장
					}
				}
				else //이미지 미첨부시
				{
					CbImage noImage = new CbImage();
					noImage.setCbimage(null); 
					noImage.setCbimagename(null);
					noImage.setCbimagesize(0L);
					noImage.setCbimagetype(null);
					noImage.setClubboard(cb);
					cbiRep.save(noImage); // 이미지파일 변수에 빈 데이터 넣고 CbImage 테이블에 저장(글 수정기능이 정상 작동되게 하기 위한 임시데이터)
				}
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
	
	// 클럽게시판 글작성 이미지 첨부 시 ckeditor에서 미리보기 기능 구현용 이미지데이터 저장
	// /ROOT/api/clubboard/ckimage
//	@RequestMapping(value="/ckimage", 
//			method={RequestMethod.POST}, 
//			consumes = {MediaType.ALL_VALUE},
//			produces= {MediaType.APPLICATION_JSON_VALUE})
//	public Map<String, Object> ckimagePOST(@ModelAttribute MultipartFile file) throws IOException
//	{
//		Map<String, Object> map = new HashMap<>();
//		try 
//		{
//			Cbckeditor cbck = new Cbckeditor();
//			if(file != null)
//			{
//				if(!file.isEmpty())
//				{
//					cbck.setCbckimage(file.getBytes());
//					cbck.setCbckimagename(file.getOriginalFilename());
//					cbck.setCbckimagesize(file.getSize());
//					cbck.setCbckimagetype(file.getContentType());
//					cbckRep.save(cbck);
//				}
//			}
//			
//			map.put("status", 200);
//		} 
//		catch (Exception e) 
//		{
//			map.put("status", 0);
//		}
//		return map;
//	}
	
	// /ROOT/api/clubboard/ckimage
//		@RequestMapping(value="/ckimage", 
//				method={RequestMethod.POST}, 
//				consumes = {MediaType.ALL_VALUE},
//				produces= {MediaType.APPLICATION_JSON_VALUE})
//		public Map<String, Object> ckimagePOST()
//		{
//			Map<String, Object> map = new HashMap<>();
//			try 
//			{
//			
//				
//				map.put("status", 200);
//			} 
//			catch (Exception e) 
//			{
//				map.put("status", 0);
//			}
//			return map;
//			
//		}
	
	
	
	// 클럽게시판 글목록 (페이지, 검색 기능)
	// /ROOT/api/clubboard/selectlist?page=&text=&option=&cno= // page = 페이지, text = 검색어, items = 검색기준(제목, 내용, 글쓴이, 전체)
	@RequestMapping(value="/selectlist", 
			method={RequestMethod.GET}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> selectlistPOST(Model model, 
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
				//1페이지 당 20글 표시
				PageRequest pageRequest = PageRequest.of(page-1, 10); 
				System.out.println(pageRequest);
//				System.out.println("text : " + text);
//				System.out.println("option : " + option);
//				System.out.println("cno : " + cno);
				
				List<ClubBoard> list = new ArrayList<>();
				
				if(option.equals("제목"))
				{
					//검색어 포함, 1페이지 20글, 글번호 내림차순
					list.addAll(cbRep.findByCbtitleContainingAndClub_cnoOrderByCbnoDesc(text, cno, pageRequest));
				}
				else if(option.equals("내용"))
				{
					list.addAll(cbRep.findByCbcontentContainingAndClub_cnoOrderByCbnoDesc(text, cno, pageRequest));
				}
				else if(option.equals("글쓴이"))
				{
					list.addAll(cbRep.findByMember_midContainingAndClub_cnoOrderByCbnoDesc(text, cno, pageRequest));
				}
//				else if(option.equals("전체"))
//				{
//					list.addAll(cbRep.findByAllOptions(text, cno, pageRequest)); //미완성
//				}
				else
				{
					list.addAll(cbRep.findByClub_cnoOrderByCbnoDesc(cno, pageRequest));
				}
				//페이지 구현용 글 개수
				long total = list.toArray().length;
//				System.out.println("total = " + total);
				model.addAttribute("total", total);
				
				// pages = 1~10 = 1, 11~20 = 2, 21~30 = 3, ...... // 한 페이지에 10글
//				System.out.println("pages : " + (total-1) / 10 + 1);
				model.addAttribute("pages", (total-1) / 10 + 1);	
				
				for(int i=0; i<list.toArray().length; i++)
				{
					ClubBoard cb = list.get(i);
					
					ClubBoard clubBoard = cbRep.findById(cb.getCbno()).orElse(null);
					
					clubBoard.setCbimageurl("/ROOT/clubboard/image/cbno=" + cb.getCbno());
				}
				
//				System.out.println("list : " + list);
				
				model.addAttribute("list", list);
				
//				System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvv");
//				System.out.println("model : " + model.toString());
//				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^");
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
	
	// 게시글 조회수 증가
	@RequestMapping(value="/updatehit", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> clubboardUpdatehit1PUT(@RequestParam(name="cbno") long cbno, @RequestParam(name="cno") long cno, @RequestHeader(name="token") String token)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				ClubBoard board = cbRep.findById(cbno).orElse(null);
				Club cnum = new Club();
				cnum.setCno(cno);
				board.setClub(cnum);
				board.setCbhit( board.getCbhit() + 1L );
				System.out.println(board.toString());
				cbRep.save(board);
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
	
	// 클럽게시판 글상세내용 + 댓글목록
	// /ROOT/api/clubboard/select?cbno=&cno=
	@RequestMapping(value="/select", 
			method={RequestMethod.GET}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> selectGET(Model model, @RequestParam(name="cbno") long cbno, @RequestParam(name="cno") long cno, @RequestHeader(name="token") String token)
	{
		Map<String, Object> map = new HashMap<>();
		try {
			if(token != null)
			{
				System.out.println("cno : " + cno);
				ClubBoard cb = cbRep.findById(cbno).orElse(null);
				cb.setCbimageurl("/ROOT/clubboard/image?cbno=" + cbno);
				
				// 댓글 목록 저장할 배열 변수
				List<CReply> replylist = crRep.findByClubboard_CbnoOrderByRenumberDesc(cbno);

				model.addAttribute("clubboard", cb); //글상세내용
				model.addAttribute("replylist", replylist); // 댓글
				
				ClubBoard prev = cbRep.findTop1ByClub_cnoAndCbnoLessThanOrderByCbnoDesc(cno, cbno); // 이전글
				ClubBoard next = cbRep.findTop1ByClub_cnoAndCbnoGreaterThanOrderByCbnoAsc(cno, cbno); // 다음글
				if(prev != null)
				{
					model.addAttribute("prev", prev.getCbno());
				}
				else
				{
					model.addAttribute("prev", 0);
				}
				if(next != null)
				{
					model.addAttribute("next", next.getCbno());
				}
				else
				{
					model.addAttribute("next", 0);
				}
				
				model.addAttribute("file", cbiRep.findByClubboard_CbnoOrderByCbimgcodeAsc(cbno));
				
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
	
	// 클럽게시판 글 첨부이미지 표시 //안씀
	// /ROOT/api/clubboard/image?cbno=
//	@RequestMapping(value="/image", 
//			method={RequestMethod.GET}, 
//			consumes = {MediaType.ALL_VALUE},
//			produces= {MediaType.APPLICATION_JSON_VALUE})
//	public Map<String, Object> imageGET(@RequestParam(name="cbno") long cbno) throws IOException
//	{
//		Map<String, Object> map = new HashMap<>();
//		try 
//		{
//			CbImage cbImage = cbiRep.findByClubboard_CbnoOrderByCbimgcodeAsc(cbno);
//			System.out.println("size : " + cbImage.getCbimagesize().toString());
//			System.out.println("length : " + cbImage.getCbimage().length);
//			if(cbImage.getCbimagesize() > 0)
//			{
//				map.put("result", cbImage);
//			}
//			
//			map.put("status", 200);
//			} 
//			catch (Exception e) 
//			{
//				map.put("status", 0);
//			}
//			return map;
//		}
	
	// 클럽게시판 글삭제
	// /ROOT/api/clubboard/delete
	@Transactional
	@RequestMapping(value="/delete", 
	method={RequestMethod.POST}, 
	consumes = {MediaType.ALL_VALUE},
	produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> delete(@RequestBody ClubBoard cb, @RequestHeader(name="token") String token)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				crRep.deleteByClubboard_cbno(cb.getCbno());		cbiRep.deleteByClubboard_cbno(cb.getCbno());
				cbRep.deleteById(cb.getCbno());
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
	
	// 글수정
	@RequestMapping(value="/update", 
			method={RequestMethod.PUT}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> updatePOST(@ModelAttribute ClubBoard cb, @ModelAttribute MultipartFile file,
			@RequestHeader(name="token") String token) throws IOException
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				System.out.println("cb : " + cb);
				ClubBoard clubboard = cbRep.findById(cb.getCbno()).orElse(null);
				Date date = clubboard.getCbregdate();
				Member mid = clubboard.getMember();
				
				cb.setCbregdate(date);
				cb.setMember(mid);
				
				cbRep.save(cb);
				if(file != null)
				{
					if(!file.isEmpty())
					{
						CbImage cbImage = new CbImage(); // 첨부한 이미지파일 받을 변수
						cbImage.setCbimage(file.getBytes()); 
						cbImage.setCbimagename(file.getOriginalFilename());
						cbImage.setCbimagesize(file.getSize());
						cbImage.setCbimagetype(file.getContentType());
						cbImage.setClubboard(cb);
						cbImage.setCbimgcode(cbiRep.findByClubboard_CbnoOrderByCbimgcodeAsc(cb.getCbno()).getCbimgcode());
						cbiRep.save(cbImage); // 이미지파일 변수에 이미지 데이터 넣고 CbImage 테이블에 저장
					}
				}
				else
				{
					CbImage cbImage = cbiRep.findByClubboard_CbnoOrderByCbimgcodeAsc(cb.getCbno());
					cbImage.setCbimgcode(cbiRep.findByClubboard_CbnoOrderByCbimgcodeAsc(cb.getCbno()).getCbimgcode());
					cbiRep.save(cbImage);
				}
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
	

	// 클럽게시판 댓글작성
	// /ROOT/api/clubboard/insertreply
	@RequestMapping(value="/insertreply", 
			method={RequestMethod.POST}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> insertrepPOST(@RequestBody Map<String, Object> cr, @RequestHeader(name="token") String token)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			if(token != null)
			{
				CReply creply = new CReply();
				System.out.println(Long.valueOf(cr.get("cbno").toString()));
				
				creply.setRecontent(cr.get("recontent").toString());
				
				Member member = new Member();
				member.setMid(jwtUtil.extractUsername(token));
				
				creply.setMember(member);
				
				ClubBoard cb = cbRep.findById(Long.valueOf(cr.get("cbno").toString())).orElse(null);
				creply.setClubboard(cb); // 댓글 작성한 글의 번호 저장
				System.out.println(creply);
				
				crRep.save(creply);
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
	
	// 클럽게시판 대댓글 작성 //미완성
	// /ROOT/api/clubboard/insertrereply?cbno=
//	@RequestMapping(value="/insertrereply", 
//			method={RequestMethod.POST}, 
//			consumes = {MediaType.ALL_VALUE},
//			produces= {MediaType.APPLICATION_JSON_VALUE})
//	public Map<String, Object> insertrerepPOST(@RequestBody CReply cr, @RequestParam(name="cbno") long cbno)
//	{
//		Map<String, Object> map = new HashMap<>();
//		try 
//		{
//			ClubBoard cb = cbRep.findById(cbno).orElse(null);
//			cr.setClubboard(cb); // 댓글 작성한 글의 번호 저장
//			crRep.save(cr);
//			map.put("status", 200);
//		}
//		catch (Exception e)
//		{
//			map.put("status", 0);
//		}
//		return map;
//	}
	
	
	// 클럽게시판 댓글삭제
	// /ROOT/api/clubboard/deletereply
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
				String mid = jwtUtil.extractUsername(token);
				System.out.println("token mid : " + mid);
				CReply member = crRep.findById(cr.getRenumber()).orElse(null);
				System.out.println("CReply mid : " + member.getMember().getMid());
				if(mid.equals(member.getMember().getMid()))
				{
					crRep.deleteById(cr.getRenumber());
					map.put("status", 200);
				}
				else
				{
					map.put("status", 0);
					map.put("result", "일치하지 않는 아이디");
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
	
	// mid 받아서 닉네임 반환
	@RequestMapping(value="/selectnick", 
			method={RequestMethod.GET}, 
			consumes = {MediaType.ALL_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> selectnickGET(@RequestBody Member member)
	{
		Map<String, Object> map = new HashMap<>();
		try 
		{
			MemberPersonal mp = pmRep.findByMember_mid(member.getMid());
			map.put("status", 200);
			map.put("result", mp);
		}
		catch (Exception e)
		{
			map.put("status", -1);
		}
		return map;
	}
}
