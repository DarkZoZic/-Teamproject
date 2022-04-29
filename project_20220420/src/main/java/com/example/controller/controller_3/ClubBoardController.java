package com.example.controller.controller_3;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
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

import com.example.repository.repository_3.CReplyRepository;
import com.example.repository.repository_3.ClubBoardImageRepository;
import com.example.repository.repository_3.ClubBoardReactionRepository;
import com.example.repository.repository_3.ClubBoardRepository;
import com.example.service.service_3.ClubBoardImageService;
import com.example.service.service_3.ClubBoardService;
import com.example.entity.entity2.ClubBoard;
import com.example.entity.entity2.CbImage;
import com.example.entity.entity2.CReply;
import com.example.entity.entity1.Reaction;

@Controller
@RequestMapping(value = "/clubboard")
public class ClubBoardController {
	
	@Autowired
	ClubBoardRepository cbRep;
	
	@Autowired
	ClubBoardService cbService;
	
	@Autowired
	ClubBoardImageService cbiService;
	
	@Autowired
	ClubBoardReactionRepository cbrRep;
	
	@Autowired
	CReplyRepository crRep;
	
	@Autowired
	ClubBoardImageRepository cbiRep;
	
	@Autowired
	ResourceLoader resLoader;
	
	// 127.0.0.1:9090/ROOT/clubboard/insert
	@GetMapping(value="/insert")
	public String insertGET()
	{
		return "/3/clubboard/insert";
	}
	
	// 클럽게시판 글쓰기
	// 127.0.0.1:9090/ROOT/clubboard/insert
	@PostMapping(value="/insert")
	public String insertPOST(@ModelAttribute ClubBoard clubBoard, @RequestParam(name="file", required=false) MultipartFile file) throws IOException
	{
		try 
		{
//			System.out.println(file.getContentType());
			CbImage cbImage = new CbImage();
			if(file != null) 
			{
				if(!file.isEmpty())// 이미지파일 첨부시
				{
					cbImage.setCbiImage(file.getBytes()); 
					cbImage.setCbiImagename(file.getOriginalFilename());
					cbImage.setCbiImagesize(file.getSize());
					cbImage.setCbiImagetype(file.getContentType());
					cbImage.setClubBoard(clubBoard);
					cbiService.insertClubBoardImage(cbImage);
				}
			}
			
			cbRep.save(clubBoard);
			
			
			return "redirect:/clubboard/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 클럽게시판 글목록 페이지
	// 127.0.0.1:9090/ROOT/clubboard/selectlist?text=&page=1
	@GetMapping(value="/selectlist")
	public String selectlistGET(Model model, @RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="text", defaultValue="") String text)
	{
		try 
		{
			//1페이지 당 20글 표시
			PageRequest pageRequest = PageRequest.of(page-1, 20); 
			System.out.println(pageRequest);
			
			//검색어 포함, 1페이지 20글, 글번호 내림차순
			List<ClubBoard> list = cbRep.findByCbTitleContainingOrderByCbNoDesc(text, pageRequest);
			model.addAttribute("list", list);
//			System.out.println(list.toString());
			
			//페이지네이션 구현용 글 개수 가져와서 model에 넣기
			long total = cbRep.countByCbTitleContaining(text);
			
			// pages = 1~20 = 1, 21~40 = 2, 41~60 = 3, ...... // 한 페이지에 20글
			model.addAttribute("pages", (total-1) / 20 + 1);
			System.out.println("total = " + total);
			System.out.println((total-1) / 20 + 1);
			return "/3/clubboard/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}

	// 클럽게시판 글 상세내용 페이지 (첨부이미지, 댓글 포함)
	// 127.0.0.1:9090/ROOT/clubboard/select?cbNo=
	@GetMapping(value="/select")
	public String selectGET(Model model, @RequestParam(name="cbNo") long cbNo
//			@RequestParam(name="rType") String rType
			)
	{
		try 
		{
			List<CReply> replylist = crRep.findByClubBoard_CbNoOrderByReNumberDesc(cbNo);
			
//			long rtype = cbrRep.selectReactionCount(cbNo, rType);
			
			model.addAttribute("clubboard", cbRep.findById(cbNo).orElse(null)); //글상세내용
			model.addAttribute("replylist", replylist); // 댓글
//			model.addAttribute("rtype", rtype); // 좋아요 수
			
			CbImage image = cbiRep.findByClubBoard_CbNoOrderByCbiImgcodeAsc(cbNo);
			System.out.println(image);
			if(image != null) // 글에 첨부된 이미지가 있으면
			{
				model.addAttribute("cbimage", image); //이미지
			}
			
			ClubBoard prev = cbRep.findTop1ByCbNoLessThanOrderByCbNoDesc(cbNo);
			ClubBoard next = cbRep.findTop1ByCbNoGreaterThanOrderByCbNoAsc(cbNo);
			model.addAttribute("prev", prev);
			model.addAttribute("next", next);
			
			return "/3/clubboard/select";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 클럽게시판 글상세 이미지 표시
	// 127.0.0.1:9090/ROOT/clubboard/image
	@GetMapping(value="/image")
	public ResponseEntity<byte[]> imageGET(@RequestParam(name="cbNo") long cbNo) throws IOException
	{
		try 
		{
			CbImage cbImage = cbiRep.findByClubBoard_CbNoOrderByCbiImgcodeAsc(cbNo);
			System.out.println(cbImage.getCbiImagesize().toString());
			System.out.println(cbImage.getCbiImage().length);
			if(cbImage.getCbiImagesize() > 0)
			{
				HttpHeaders headers = new HttpHeaders();
				if(cbImage.getCbiImagetype().equals("image/jpeg")) {
					System.out.println("///////////////////////////////bbb/////////////////////////");
					headers.setContentType(MediaType.IMAGE_JPEG);
				}
				else if(cbImage.getCbiImagetype().equals("image/png")) {
					headers.setContentType(MediaType.IMAGE_PNG);
				}
				else if(cbImage.getCbiImagetype().equals("image/gif")) {
					headers.setContentType(MediaType.IMAGE_GIF);
				}
				
				// 이미지 byte[], headers, HttpStatus.Ok
				ResponseEntity<byte[]> response = new ResponseEntity<>(cbImage.getCbiImage(), headers, HttpStatus.OK );	
				System.out.println("///////////////////////aaa///////////////////////");
				return response;
			}
			else
			{
				InputStream is = resLoader.getResource("classpath:/static/images/default.png").getInputStream();
				HttpHeaders headers = new HttpHeaders();
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
	
	// 클럽게시판 글삭제
	// 127.0.0.1:9090/ROOT/clubboard/delete?cbNo=
	@Transactional
	@PostMapping(value="/delete")
	public String deletePOST(@RequestParam(name="cbNo") long cbNo)
	{
		try 
		{
			cbiRep.deleteByClubBoard_cbNo(cbNo); //글에 첨부된 이미지 삭제
			crRep.deleteByClubBoard_cbNo(cbNo); // 글에 달린 댓글삭제
			cbRep.deleteById(cbNo); //글 삭제
			
			return "redirect:/clubboard/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 클럽게시판 글수정 페이지
	// 127.0.0.1:9090/ROOT/clubboard/update?cbNo=
	@GetMapping(value="/update")
	public String updateGET(Model model, @RequestParam(name="cbNo") long cbNo)
	{
		try 
		{
			model.addAttribute("clubboard", cbRep.findById(cbNo).orElse(null)); //글내용 수정페이지로 넘겨주기
			model.addAttribute("cbimage", cbiRep.findByClubBoard_CbNoOrderByCbiImgcodeAsc(cbNo)); //이미지파일 데이터 넘겨주기
			return "/3/clubboard/update";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 클럽게시판 글수정
	// 127.0.0.1:9090/ROOT/clubboard/update?cbNo=
	@PostMapping(value="/update")
	public String updatePOST(@ModelAttribute ClubBoard clubboard, @ModelAttribute CbImage cbimage, @RequestParam(name="cbimage", required=false) MultipartFile file)
	{
		try 
		{
			if(file != null) //파일 첨부시 cbimage에 첨부한 파일 데이터 넣기
			{
				if(!file.isEmpty())
				{
					cbimage.setCbiImgcode(cbimage.getCbiImgcode());
					cbimage.setCbiImage(file.getBytes());
					cbimage.setCbiImagename(file.getOriginalFilename());
					cbimage.setCbiImagesize(file.getSize());
					cbimage.setCbiImagetype(file.getContentType());
				}
				
			}
			else //파일 미첨부시 
			{
				CbImage oldImage = cbiRep.findByClubBoard_CbNoOrderByCbiImgcodeAsc(clubboard.getCbNo());
				if(oldImage.getCbiImage() != null) 
				{
					if(oldImage.getCbiImage().length > 0)//글에 기존에 올린 이미지파일이 있으면 해당 이미지파일 데이터 가져오기
					{
						cbimage.setCbiImgcode(oldImage.getCbiImgcode());
						cbimage.setCbiImage(oldImage.getCbiImage());
						cbimage.setCbiImagename(oldImage.getCbiImagename());
						cbimage.setCbiImagesize(oldImage.getCbiImagesize());
						cbimage.setCbiImagetype(oldImage.getCbiImagetype());
//						System.out.println("oldimage : "+cbimage.getCbiImagetype().toString());
					}
				}
				else //없으면 null
				{
					cbimage.setCbiImage(null);
					cbimage.setCbiImagename(null);
					cbimage.setCbiImagesize(null);
					cbimage.setCbiImagetype(null);
				}
			}
			
			System.out.println(clubboard.toString());
			
			cbRep.save(clubboard);
			cbiRep.save(cbimage);
			
			return "redirect:/clubboard/select?cbNo=" + clubboard.getCbNo();
				
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 클럽게시판 댓글쓰기
	// 127.0.0.1:9090/ROOT/clubboard/insertreply?cbNo=
	@PostMapping(value="/insertreply")
	public String insertreplyPOST(@RequestBody CReply cReply, @RequestParam(name="cbNo") ClubBoard cbNo)
	{
		try 
		{
			System.out.println(cReply.toString());
			System.out.println(cbNo);
			cReply.setClubBoard(cbNo);
			cReply.setRParentnumber(0L);
			System.out.println(cReply.toString());
			
			crRep.save(cReply);
			return "redirect:/clubboard/select?cbNo=" + cReply.getClubBoard().getCbNo();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	
	// 클럽게시판 댓글삭제
	// 127.0.0.1:9090/ROOT/clubboard/deletereply
	// {"reNumber":""}
	@PostMapping(value="/deletereply")
	public String deletereplyPOST(@RequestBody CReply creply)
	{
		try 
		{
			crRep.deleteById(creply.getReNumber());
			return "redirect:/clubboard/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	// 127.0.0.1:9090/ROOT/clubboard/insertreaction
	// 클럽게시판 글 반응(좋아요/엄지) 넣기
	@PostMapping(value="/insertreaction")
	public String insertreactionPOST(@RequestBody Reaction reaction, @RequestParam(name="cbNo") ClubBoard cbNo) 
	// 반응종류 -> <input type="submit" name="reaction.rType" value="좋아요 or 따봉" />
	{
		try 
		{
			reaction.setClubBoard(cbNo);
			cbrRep.save(reaction); //rType(반응종류) = "좋아요" or "따봉"
			return "redirect:/clubboard/select?cbNo=" + cbNo.getCbNo();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	@PostMapping(value="/prev")
	public String prevPOST(@RequestParam(name="cbNo") long cbNo, Model model)
	{
		try 
		{
			ClubBoard prev = cbRep.findTop1ByCbNoLessThanOrderByCbNoDesc(cbNo);
			
			if(prev == null)
			{
				return "redirect:/clubboard/select?cbNo=" + cbNo;
			}
			model.addAttribute("clubboard", prev);
			return "redirect:/clubboard/select?cbNo=" + prev.getCbNo();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	@PostMapping(value="/next")
	public String nextPOST(@RequestParam(name="cbNo") long cbNo, Model model)
	{
		try 
		{
			ClubBoard next = cbRep.findTop1ByCbNoGreaterThanOrderByCbNoAsc(cbNo);
			
			if(next == null)
			{
				return "redirect:/clubboard/select?cbNo=" + cbNo;
			}
			model.addAttribute("clubboard", next);
			return "redirect:/clubboard/select?cbNo=" + next.getCbNo();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}
}
