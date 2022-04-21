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
import com.example.service.service_3.ClubBoardService;
import com.example.entity.entity2.ClubBoard;
import com.example.entity.entity2.CbImage;

@Controller
@RequestMapping(value = "/clubboard")
public class ClubBoardController {
	@Autowired
	ClubBoardRepository cbRep;
	
	@Autowired
	ClubBoardService cbService;
	
	@PostMapping(value="/insert")
	public String insertPOST(@ModelAttribute ClubBoard clubboard, @ModelAttribute CbImage cbimage, @RequestParam(name="cbimage") MultipartFile file) throws IOException
	{
		try {
			cbimage.setCbiImage(file.getBytes()); //cbimage entity cbiimage 타입 byte[]로 변경해야됨
			cbimage.setCbiImagename(file.getOriginalFilename());
			cbimage.setCbiImagesize(file.getSize());
			cbimage.setCbiImagetype(file.getContentType());
			
			int ret = cbService.insertClubBoard(clubboard, cbimage);
			if(ret == 1)
			{
				return "/clubboard/selectlist";
			}
		} catch (Exception e) {
			
		}
	}
	
	@GetMapping(value="/selectlist")
	public String selectlistGET(Model model, @RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="text", defaultValue="") String text)
	{
		try 
		{
			//1페이지 당 20글 표시
			PageRequest pageRequest = PageRequest.of(page-1, 20); 
			
			//검색어 포함, 1페이지 20글, 글번호 내림차순
			List<ClubBoard> list = cbRep.findByTitleContainingOrderByNoDesc(text, pageRequest);
			model.addAttribute("list", list);
			
			//페이지네이션 구현용 글 개수 가져와서 model에 넣기
			long total = cbRep.countByTitleContaining(text);
			
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

}
