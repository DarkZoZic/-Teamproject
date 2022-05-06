package com.example.controller.controller_4;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.entity.entity1.Member;
import com.example.entity.entity2.BImage;
import com.example.entity.entity2.Board1;
import com.example.entity.entity2.CReply;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.Board1ImageRepository;
import com.example.repository.repository_4.Board1Repository;
import com.example.repository.repository_4.Member1Repository;
import com.example.service.UserDetailsServiceImpl;
import com.example.service.service_4.Board1Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/board1")
public class Board1RestController {

    @Autowired 
    Board1Repository b1Repository;

    @Autowired
    Board1ImageRepository b1IRepository;

    @Autowired
    Member1Repository mRepository;

    @Autowired 
    Board1Service b1Service;
    
    @Autowired 
    JwtUtil jwtUtil;
    
    @Autowired 
    UserDetailsServiceImpl userDetailsService;

    // int PAGECNT = 10
    // global.properties 사용하기. 나중에 숫자 바꾸고 싶은대로 바꾸면 됨
    @Value("${board.page.count}") int PAGECNT;

    //127.0.0.1:9090/ROOT/board1/insert
    @RequestMapping(value = "/insert", 
        method = {RequestMethod.POST},
        consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> InsertPost(
        @ModelAttribute Board1 board1,
        @RequestHeader (name = "token") String token ) {

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Member memberEntity = new Member();
            memberEntity.setMId(userid);
            System.out.println(memberEntity);

            board1.setMember(memberEntity);
            System.out.println(board1.toString());

            if(token !=null) {
                int ret = b1Service.insertBoard1One(board1);
                System.out.println(board1.toString());
                if(ret == 1){
                    map.put("status", 200); // 성공
                    map.put("result", "등록완료");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }


    // 이미지 넣는거 왜 안되지?
   
    //     @RequestMapping(value = "/insert", 
    //     method = {RequestMethod.POST},
    //     consumes = {MediaType.ALL_VALUE},
    //     produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> InsertPost(
    //     @ModelAttribute Board1 board1,
    //     @ModelAttribute BImage bImage,
    //     @RequestHeader (name = "token") String token,
    //     @RequestParam(name="file", required = false) MultipartFile file ) throws IOException {

    //     Map<String ,Object> map = new HashMap<>();

    //     try{
    //         System.out.println(file);

    //         if(file != null){
    //             if(!file.isEmpty()){
    //                 bImage.setBiImagetype(file.getContentType());
    //                 bImage.setBiImagename(file.getOriginalFilename());
    //                 bImage.setBiImagesize(file.getSize());
    //                 bImage.setBiImage(file.getBytes()); 
    //             }
    //         }
    //         b1IRepository.save(bImage);

    //         // 토큰 추출
    //         String userid = jwtUtil.extractUsername(token);
    //         System.out.println("USERNAME ==>" + userid);

    //         Member memberEntity = new Member();
    //         memberEntity.setMId(userid);
    //         System.out.println(memberEntity);

    //         board1.setMember(memberEntity);
    //         System.out.println(board1.toString());

    //         int ret = b1Service.insertBoard1One(board1);
    //         System.out.println(board1.toString());
    //         if(ret == 1){
    //             map.put("status", 200); // 성공
    //             map.put("result", "등록완료");
    //         }
            
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         map.put("status", 0); // 실패
    //     }
    //     return map;
    // }

 // Bimage에 이미지를 넣고 나중에 board1의 번호와 연결시키는 방식?
    // @RequestMapping(value = "/image", 
    //     method = {RequestMethod.POST},
    //     consumes = {MediaType.ALL_VALUE},
    //     produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> ImagePost(
    //     @ModelAttribute BImage bImage,
    //     @RequestParam(name = "bNo") long bNo,
    //     @RequestHeader (name = "token") String token ) {

    //     Map<String ,Object> map = new HashMap<>();

    //     try{
    //         // 토큰 추출
    //         String userid = jwtUtil.extractUsername(token);
    //         System.out.println("USERNAME ==>" + userid);

    //         Member memberEntity = new Member();
    //         memberEntity.setMId(userid);
    //         System.out.println(memberEntity);

    //         board1.setMember(memberEntity);
    //         System.out.println(board1.toString());

    //         if(token !=null) {
    //             int ret = b1Service.insertBoard1One(board1);
    //             System.out.println(board1.toString());
    //             if(ret == 1){
    //                 map.put("status", 200); // 성공
    //                 map.put("result", "등록완료");
    //             }
    //         }
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         map.put("status", 0); // 실패
    //     }
    //     return map;
    // }

    // 127.0.0.1:9090/ROOT/board1/delete
    // {"bno":3}
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardDeletePost(@RequestBody Board1 board1,
                                            @RequestHeader (name = "token")String token){
        // 키를 알고 보내야 함. 틀리면 안감. er다이어그램 보면 됨

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Board1 board = b1Repository.getById(board1.getBNo());
            System.out.println(board.toString());

            System.out.println("번호"+board1.getBNo());

            if(userid.equals( board.getMember().getMId() )){
                // Board1 result = b1Service.selectBoard1One(board.getBNo());

                // 삭제
                int ret = b1Service.deleteBoard1One(board.getBNo());
                if(ret == 1){
                    map.put("status", 200); // 성공
                }
            }
            else if (!userid.equals( board.getMember().getMId() )){
                map.put("status", 0); 
            }
           
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", -1); // 실패
        }
        return map;
    }

    // 127.0.0.1:9090/ROOT/board1/update
    // 제목, 내용, 번호
    // {"bno":2, "btitle":"222", "bcontent":"222"}
    @RequestMapping(value = "/update", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardUpdatePost(
            @ModelAttribute Board1 board1, 
            @RequestHeader (name = "TOKEN")String token ) {

        System.out.println(board1.toString());

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Board1 board = b1Repository.getById(board1.getBNo());
            System.out.println(board.toString());

            System.out.println("=====" + board.getMember().getMId());

            if(userid.equals( board.getMember().getMId() )){
                Board1 result = b1Service.selectBoard1One(board.getBNo());

                // 수정
                result.setBTitle(board1.getBTitle());
                result.setBContent(board1.getBContent());

                int ret = b1Service.updateBoard1One(result);
                if(ret == 1){
                    map.put("status", 200); // 성공
                }
            }
            else if (!userid.equals( board.getMember().getMId() )){
                map.put("status", 0); 
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", -1); // 실패
        }
        return map;
    }

    // 127.0.0.1:9090/ROOT/board1/selectone?bno=2
    @RequestMapping(value = "/selectone", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardSelectOneGET(
        @RequestParam(name = "bNo") long bNo,
        @RequestHeader (name = "token") String token ){

        Map<String ,Object> map = new HashMap<>();
        try{
            if(token != null){
                Board1 board1 = b1Service.selectBoard1One(bNo);
                if(board1 != null){
                    map.put("status", 200); // 성공 
                    map.put("result", board1);
                }
            }
               
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
       
    }

    // 게시판 목록(페이지네이션만 있음, 검색x)
    // 127.0.0.1:9090/ROOT/api/board/selectlist?page=1
    // @RequestMapping(value = "/selectlist", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
    //                 produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> boardSelectListGET(@RequestParam(name = "page") int page){

    //     Map<String ,Object> map = new HashMap<>();
    //     try{
    //         // List<Board1> list = b1Service.selectBoard1List(bNo);
    //         map.put("status", 200); // 성공

    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         map.put("status", 0); // 실패
    //     }
    //     return map;


    //     // List<BoardDTO> list = bMapper.selectBoardList((page * PAGECNT) - (PAGECNT -1), page * PAGECNT);
    //     // if(list != null){
    //     //     map.put("status", 200); // 성공
    //     //     map.put("result", list); 
    //     // }

    // // 페이지네이션                         목록이니까 List
    // // @Select({
    // //     "SELECT * FROM (",
    // //         " SELECT B.*, ROW_NUMBER() OVER (ORDER BY BNO DESC) ROWN ",
    // //         " FROM BOARD B ",
    // //     " ) WHERE ROWN BETWEEN #{start} AND #{end}"
    // // })
    // // public List<BoardDTO> selectBoardList(@Param(value = "start") int s, @Param(value = "end") int e);

    // }

    ////////////////////////////////////////////////////////////////////

    // 127.0.0.1:9090/ROOT/board1/selectlist1?page=1
    // @RequestMapping(value = "/selectlist1", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
    //                 produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> boardSelectListGET(
        
    //     @RequestParam(name = "page", defaultValue = "1") int page,
    //     @RequestParam(name = "txt", defaultValue = "") String txt ){

    //     Map<String ,Object> map = new HashMap<>();
    //     try{
    //         // 페이지네이션
    //         PageRequest pageRequest = PageRequest.of(page-1, PAGECNT);

    //         // List<Board1> list = b1Service.selectBoard1List(bTitle, pageRequest);
    //         List<Board1> list = b1Repository.findByBTitleContainingOrderByBNoDesc(txt, pageRequest);
    //         System.out.println(list.size());

    //         if(!list.isEmpty()){
    //             map.put("status", 200);
    //             map.put("result", list);
    //         }

    //         // 검색어, 페이지네이션
    //         // List<Board1> list = b1Repository.findByBTitleContainingOrderByBNoDesc(bTitle, pageRequest);
    //         // List<Board1> blist = b1Service.selectBoard1List(bNo, pageable, bTitle);
    //         // List<Board1> blist = b1Service.selectBoard1List(bNo, bTitle, pageable);
           
    //         // System.out.println(list);
    //         // if(!list.isEmpty()){
    //         //     map.put("status", 200); // 성공
    //         //     map.put("result", list); 
    //         // }

    //         // 전체개수
    //         // long total = b1Repository.countByBTitleContaining(bTitle);
    //         // map.put("pages", (total-1)/PAGECNT +1);

    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         map.put("status", 0); // 실패
    //     }
    //     return map;
    // }



    // 게시물 조회수 1증가 시킴
    // 127.0.0.1:9090/ROOT/board1/updatehit?bno=2
    @RequestMapping(value = "/updatehit", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardUpdateHitGET(
        @RequestParam(name = "bNo") long bNo,
        @RequestHeader (name = "token")String token){

        Map<String ,Object> map = new HashMap<>();
        try{
            if(token != null){
                int ret = b1Service.updateBoard1HitOne(bNo);
                if(ret == 1){
                    map.put("status", 200); // 성공
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }


    // 일괄삭제
    // 127.0.0.1:9090/ROOT/board1/deletebatch
    // @RequestMapping(value = "/deletebatch", method = {RequestMethod.POST}, consumes = {MediaType.ALL_VALUE},
    //                 produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> deleteBatch(
    //     @RequestParam(name = "bNo") Long[] bNo,
    //     @RequestHeader (name = "token")String token){

    //     Map<String ,Object> map = new HashMap<>();
    //     try{
    //         // 토큰 추출
    //         String userid = jwtUtil.extractUsername(token);
    //         System.out.println("USERNAME ==>" + userid);


    //         // Board1 board = b1Repository.getById(board1.getBNo());

    //         Board1 board = b1Repository.
    //         System.out.println(board.toString());

    //         System.out.println("번호" + board1.getBNo());

    //         if(userid.equals( bNo.getMember().getMId() )){
    //             // 삭제
    //             b1Service.deleteBoard1Batch(bNo);
    //             map.put("status", 200); // 성공
                
    //         }
    //         else if (!userid.equals( board.getMember().getMId() )){
    //             map.put("status", 0); 
    //         }
           
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         map.put("status", -1); // 실패
    //     }
    //     return map;
    // }

    // 일괄 수정, 삭제
    // @GetMapping(value = "/deleteupdatebatch")
    // public String deleteupdatebatchGET(
    //     Model model,
    //     @RequestParam(name = "btn") String btn,
    //     @RequestParam(name = "no") Long[] no  ){
    //         // 버튼(btn)이랑 체크박스(no) 받기

    //     System.out.println(btn);
    //     System.out.println(no[0]);
    //     if(btn.equals("일괄수정")){

    //         List<ItemEntity> list = iService.selectItemEntityIn(no);
    //         model.addAttribute("list", list);
            
    //         return "/seller/updateitem_batch";

    //     }
    //     else if(btn.equals("일괄삭제")){

    //        iService.deleteItemBatch(no);
    //     }

    //     return "redirect:/seller/home";

    // }

   


    
}
