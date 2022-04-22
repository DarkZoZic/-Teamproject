package com.example.controller.controller_3;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.repository.repository_3.ClubBoardRepository;
import com.example.service.service_3.ClubBoardImageService;
import com.example.service.service_3.ClubBoardService;
import com.example.entity.entity2.ClubBoard;
import com.example.entity.entity2.CbImage;
import com.example.entity.entity2.CReply;

@Controller
@RequestMapping(value = "/clubboard")
public class ClubBoardController {
	@Autowired
	ClubBoardRepository cbRep;
	
	@Autowired
	ClubBoardService cbService;
	
	@Autowired
	ClubBoardImageService cbiService;
	
	@GetMapping(value="/insert")
	public String insertGET()
	{
		return "/clubboard/insert";
	}
	
	// 127.0.0.1:9090/ROOT/clubboard/insert
	@PostMapping(value="/insert")
	public String insertPOST(@ModelAttribute ClubBoard clubboard, @ModelAttribute CbImage cbimage, @RequestParam(name="cbimage") MultipartFile file) throws IOException
	{
		try 
		{
			cbimage.setCbiImage(file.getBytes()); //cbimage entity cbiimage 타입 byte[]로 변경해야됨
			cbimage.setCbiImagename(file.getOriginalFilename());
			cbimage.setCbiImagesize(file.getSize());
			cbimage.setCbiImagetype(file.getContentType());
			
			int ret = cbService.insertClubBoard(clubboard);
			int ret1 = cbiService.insertClubBoardImage(cbimage);
			if(ret == 1)
			{
				if(ret1 == 1)
				{
					return "/clubboard/selectlist";
				}
				return "/clubboard/insert";
			}
			return "/clubboard/insert";
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "/";
		}
	}
	
	// 127.0.0.1:9090/ROOT/clubboard/selectlist
	@GetMapping(value="/selectlist")
	public String selectlistGET(Model model, @RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="text", defaultValue="") String text)
	{
		try 
		{
			//1페이지 당 20글 표시
			PageRequest pageRequest = PageRequest.of(page-1, 20); 
			
			//검색어 포함, 1페이지 20글, 글번호 내림차순
			List<ClubBoard> list = cbRep.findByCbTitleContainingOrderByCbNoDesc(text, pageRequest);
			model.addAttribute("list", list);
			
			//페이지네이션 구현용 글 개수 가져와서 model에 넣기
			long total = cbRep.countByCbTitleContaining(text);
			
			//1~20 = 1, 21~40 = 2, 41~60 = 3, ......
			model.addAttribute("pages", (total-1) / 20 + 1);
			return "/clubboard/selectlist";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "redirect:/";
		}
	}

	// 127.0.0.1:9090/ROOT/clubboard/select
	@GetMapping(value="/select")
	public String selectGET(Model model, @RequestParam(name="cbNo") long cbNo)
	{
		try 
		{
			model.addAttribute("clubboard", cbService.selectClubBoard(cbNo));
			return "/clubboard/select?cbNo=" + cbNo;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "/";
		}
	}
	
	// 127.0.0.1:9090/ROOT/clubboard/insertreply
	@PostMapping(value="/insertreply")
	public String insertreplyPOST(@ModelAttribute CReply cReply)
	{
		try 
		{
			cbService.insertCReply(cReply);
			return "/clubboard/select?cbNo=" + cReply.getClubBoard().getCbNo();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "/";
		}
	}
}
