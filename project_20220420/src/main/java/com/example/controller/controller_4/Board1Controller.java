package com.example.controller.controller_4;

import java.util.List;

import com.example.entity.entity2.BImage;
import com.example.entity.entity2.Board1;
// import com.example.repository.repository_4.Board1Repository;
import com.example.repository.repository_4.Board1Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/board1")
public class Board1Controller {

    @Autowired Board1Repository b1Repository;

    @Autowired ResourceLoader resLoader;

    @GetMapping(value = "/insert")
    public String insertGET(){
        return null;
    }

    @PostMapping(value = "/insertaction")
    public String insertPOST( Model model,
                            @ModelAttribute Board1 board1,
                            @ModelAttribute BImage bImage,
                            @RequestParam(name = "timage") MultipartFile file){
        try{
            // 첨부 안했을 때 처리
            // bImage.setBiImage(file.getBytes());
            // bImage.setBiImagename(file.getOriginalFilename());
            // bImage.setBiImagetype(file.getContentType());
            // bImage.setBiImagesize(file.getSize());

            b1Repository.save(board1); //? 뭘로 해야하지
            return "redirect:/board/insert";
            
        }
        catch(Exception e){
            e.printStackTrace();
            return "redirect:/home";
        }
    }

    // @GetMapping(value = "/selectlist")
    // public String selectListGET(
    //     Model model,
	// 	@RequestParam(name = "page", defaultValue = "1") int page,
	// 	@RequestParam(name = "title", defaultValue = "") String title ){
    
    //     // 페이지네이션 (시작페이지(0부터. 1페이지는 0임), 개수)
    //     PageRequest pageRequest = PageRequest.of(page-1, PAGECNT);

    //     // 검색어, 페이지네이션
    //     List<Board1> list 
    //         = b1Repository.findByTitleContainingOrderByNoDesc(title, pageRequest);
    //     model.addAttribute("list", list);

    //     // 전체개수
    //     // 10 => 1, 23 => 3, 45 => 5
    //     long total = bRepository.countByTitleContaining(title);
    //     model.addAttribute("pages", (total-1)/PAGECNT +1 );

    //     return "/board/selectlist";

    //     return "/board/selectlist"; 
    // }

    
    
}

