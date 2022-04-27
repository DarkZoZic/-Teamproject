package com.example.controller.controller_4;

import java.util.List;

import com.example.entity.entity2.BImage;
import com.example.entity.entity2.Board1;
import com.example.entity.entity2.CReply;
import com.example.repository.repository_4.Board1ImageRepository;
import com.example.repository.repository_4.Board1ReplyRepository;
import com.example.repository.repository_4.Board1Repository;
import com.example.repository.repository_4.Board1Repository;
import com.example.service.service_4.Board1Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired Board1Service b1Service;
    @Autowired Board1Repository b1Repository;
    // @Autowired Board1ReplyRepository b1replyRepository;
    @Autowired Board1ImageRepository b1ImageRepository;

    @Value("${board.page.count}") int PAGECNT;

    // 글쓰기
    @GetMapping(value = "/insert")
    public String insertGET(){
        return null;
    }

    @PostMapping(value = "/insert")
    public String insertPOST( Model model,
                            @ModelAttribute Board1 board1){
        try{

            // 첨부 안했을 때 처리

            // 첨부했을 때
            // bImage.setBiImage(file.getBytes());
            // bImage.setBiImagename(file.getOriginalFilename());
            // bImage.setBiImagetype(file.getContentType());
            // bImage.setBiImagesize(file.getSize());
            
            // b1ImageRepository.save(bImage);

            b1Service.insertBoard1One(board1);
            // b1Repository.save(board1);

            return "redirect:/home";
            
        }
        catch(Exception e){
            e.printStackTrace();
            return "redirect:/home";
        }
    }

    // 목록
    // http://127.0.0.1:9090/ROOT/board1/selectlist?page=1&title=y
    // @GetMapping(value = "/selectlist")
    // public String selectListGET(
    //     Model model,
	// 	@RequestParam(name = "page", defaultValue = "1") int page,
	// 	@RequestParam(name = "title", defaultValue = "") String title ){
    //     try{

    //         // 페이지네이션 (시작페이지(0부터. 1페이지는 0임), 개수)
    //         PageRequest pageRequest = PageRequest.of(page-1, PAGECNT);

    //         // 검색어, 페이지네이션
    //         List<Board1> list 
    //             = b1Repository.findByBtitleContainingOrderByBnoDesc(title, pageRequest);
    //         model.addAttribute("list", list);

    //         // 전체개수
    //         // 10 => 1, 23 => 3, 45 => 5
    //         long total = b1Repository.countByTitleContaining(title);
    //         model.addAttribute("pages", (total-1)/PAGECNT +1 );

    //         return "/board/selectlist";
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return "redirect:/board/selectlist";

    //     }
    // }

    // 상세페이지
    // @GetMapping(value = "/selectone")
    // public String selectOneGET(Model model, 
    // @RequestParam(name = "bNo") long bNo,
    // @RequestParam(name = "biImgcode") long biImgcode ){

    //     try{
    //         Board1 board1 = b1Repository.findById(bNo).orElse(null);
    //         BImage bImage = b1ImageRepository.findById(biImgcode).orElse(null);
    //         //BoardEntity board = bRepository.findById(no).orElse(null);
    //         //System.out.println(board.getReplyList());

    //         // 이미지 url 정보 포함 후 전송
    //         board1.setImageurl(request.getContextPath() + "/board/image?no=" + no);
    //         model.addAttribute("board", board);

            

    //         // 이전글
    //         BoardListProjection board1 = bRepository.findTop1ByNoLessThanOrderByNoDesc(no);
        
    //         if(board1 != null){
    //             System.out.println(board1.toString());
    //             model.addAttribute("prev", board1.getNo());

    //         }
    //         else{
    //             model.addAttribute("prev", 0L);
    //         }

    //         // 다음글
    //         BoardListProjection board2 = bRepository.findTop1ByNoGreaterThanOrderByNoAsc(no);

    //         if(board2 != null){
    //             model.addAttribute("next", board2.getNo());
    //         }
    //         else{
    //             model.addAttribute("next", 0L);
    //         }

    //         // 댓글
    //         List<CReply> repList = b1replyRepository.findByBoard1_bNoOrderByNoAsc(board1.getBNo());
    //         model.addAttribute("repList", repList);
    //         model.addAttribute("brd", board1);
            
    //         return "/board/selectone";

    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return "redirect:/board/selectlist";
    //     }


    // }

    // 수정
    // @GetMapping(value = "/update")
    // public String updateGET(Model model, @RequestParam(name = "bNo") long bNo){
    //     try{
    //         Board1 board1 = b1Repository.findById(bNo).orElse(null);
    //         model.addAttribute("brd", board1);
    //         return "/board/update";

    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return "redirect:/board/selectone";
    //     }
       
    // }

    // @PostMapping(value = "/update")
    // public String updatePOST(Model model, @ModelAttribute Board1 board1){

    //     try{

    //         // 기존 내용을 읽음
    //         Board1 uptboard = b1Repository.findById(board1.getBNo()).orElse(null);

    //         // 변경할 항목만 board1에 다시 저장 (기존 내용에서 수정된 내용 덮어씌우기)
    //         uptboard.setBTitle( board1.getBTitle() );
    //         uptboard.setBContent( board1.getBContent() );

    //         // 수정하고 저장하기
    //         b1Repository.save(uptboard);

    //         return "redirect:/board/selectlist?bNo=" + board1.getBNo();

    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return "redirect:/board/selectone";
    //     }
	
    // }

    // 삭제
    // @PostMapping(value = "/delete")
    // public String deletePOST( @RequestParam(name = "bNo") long bNo ) {
    //     try{
    //         System.out.println(bNo);
    //         bService.deleteBoard1One(bNo);
    
    //         return "redirect:/board/selectlist";
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return "redirect:/board/selectone";
    //     }
       
        
    // }
    
    
}

