package com.example.controller.controller_4;

import java.util.List;

import com.example.entity.entity2.Board1;
import com.example.repository.repository_4.Board1ImageRepository;
import com.example.repository.repository_4.CreplyRepository;
import com.example.repository.repository_4.Board1Repository;
import com.example.service.service_4.Board1Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping(value = "/board1")
public class Board1Controller {

    @Autowired Board1Service b1Service;
    @Autowired Board1Repository b1Repository;
    @Autowired CreplyRepository b1replyRepository;
    @Autowired Board1ImageRepository b1ImageRepository;

    // @Value("${board.page.count}") int PAGECNT;

    // 글쓰기
    @GetMapping(value = "/insert")
    public String insertGET(){
        return "/4/board1/insert";
    }

    // @PostMapping(value = "/insert")
    // public String insertPOST( Model model,
    //                         @ModelAttribute Board1 board1){
    //     try{
    //         System.out.println(board1.toString());

    //         // 첨부 안했을 때 처리

    //         // 첨부했을 때
    //         // bImage.setBiImage(file.getBytes());
    //         // bImage.setBiImagename(file.getOriginalFilename());
    //         // bImage.setBiImagetype(file.getContentType());
    //         // bImage.setBiImagesize(file.getSize());
            
    //         // b1ImageRepository.save(bImage);

    //         b1Service.insertBoard1One(board1);
    //         // b1Repository.save(board1);

    //         return "redirect:/board1/insert";
            
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return "redirect:/home";
    //     }
    // }

    // // 수정
    // @GetMapping(value = "/update")
    // public String updateGET(Model model, @RequestParam(name = "bNo") long bNo){
    //     try{
    //         System.out.println(bNo);

    //         Board1 board1 = b1Repository.findById(bNo).orElse(null);
    //         model.addAttribute("brd", board1);
    //         // return "/board/update";
    //         return "/home";

    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         // return "redirect:/board/selectone";
    //         return "redirect:/home";
    //     }
       
    // }

    // @PostMapping(value = "/update")
    // public String updatePOST(Model model, @ModelAttribute Board1 board1){
    //     try{
    //         // 기존 내용을 읽음
    //         Board1 updateBoard = b1Repository.findById(board1.getBNo()).orElse(null);

    //         // 변경할 항목만 board1에 다시 저장 (기존 내용에서 수정된 내용 덮어씌우기)
    //         updateBoard.setBTitle( board1.getBTitle() );
    //         updateBoard.setBContent( board1.getBContent() );

    //         // 수정하고 저장하기
    //         b1Repository.save(updateBoard);

    //         return "redirect:/home";
    //         // return "redirect:/board/selectlist?bNo=" + board1.getBNo();

    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         // return "redirect:/board/selectone";
    //         return "redirect:/home";
    //     }
	
    // }

    // // 삭제
    // @PostMapping(value = "/delete")
    // public String deletePOST( @RequestParam(name = "bNo") long bNo ) {
    //     try{
    //         System.out.println(bNo);
    //         b1Service.deleteBoard1One(bNo);
    
    //         return "redirect:/board/selectlist";
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return "redirect:/board/selectone";
    //     }
    // }

    // // 일괄삭제
    // @PostMapping(value = "/deletebatch")
    // public String deleteBatchPOST(){
    //     try{
    //         return "redirect:/home";

    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return "redirect:/home";
    //     }
    // }






    

    // 목록
    // // http://127.0.0.1:9090/ROOT/board1/selectlist?page=1&title=y
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

    //         return "/4/board1/selectlist";
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         return "redirect:/board1/selectlist";

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
    //         Board1 board1 = b1Repository.findTop1ByNoLessThanOrderByNoDesc(bNo);
        
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

    // @GetMapping("/select9")
    // public String selectListGET(
    //     Model model,
    //     @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
    //     Pageable pageable,
    //     @RequestParam(required = false, defaultValue = "") String field,
    //     @RequestParam(required = false, defaultValue = "") String word ){

    //     Page<Board1> blist = b1Repository.findAll(pageable);
    //     if(field.equals("bTitle")){
    //         blist = b1Repository.findByBTitleContaining(word, pageable);
    //     }
    //     else if(field.equals("bContent")){
    //         blist = b1Repository.findByBContentContaining(word, pageable);
    //     }

    //     int pageNumber = blist.getPageable().getPageNumber(); // 현재페이지
    //     int totalPages = blist.getTotalPages(); //총 페이지 수. 검색에따라 10개면 10개.

    //     int pageBlock = 5; //블럭의 수 1, 2, 3, 4, 5
    //     int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //현재 페이지가 7이라면 1*

    //     int endBlockPage = startBlockPage+pageBlock-1; //6+5-1=10. 6,7,8,9,10해서 10.

    //     endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;

    //     model.addAttribute("startBlockPage", startBlockPage); 
    //     model.addAttribute("endBlockPage", endBlockPage);
    //     model.addAttribute("blist", blist);

    //     return "/user/userlist";
    // }

    // @GetMapping("/selectlist8")
    // public String searchGET(String bTitle, Model model){

    //     List<Board1> searchList = b1Repository.findbyBtitleContaining(bTitle);
    //     model.addAttribute("searchList", searchList);

    //     return "/home";
    // }
    
    
}

