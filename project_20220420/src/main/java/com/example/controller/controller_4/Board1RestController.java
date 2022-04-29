package com.example.controller.controller_4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.entity.entity1.Member;
import com.example.entity.entity2.Board1;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.Board1Repository;
import com.example.service.UserDetailsServiceImpl;
import com.example.service.service_4.Board1Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/board1")
public class Board1RestController {

    @Autowired 
    Board1Repository b1Repository;

    @Autowired 
    Board1Service b1Service;
    
    @Autowired 
    JwtUtil jwtUtil;
    
    @Autowired 
    UserDetailsServiceImpl userDetailsService;


    // int PAGECNT = 10
    // global.properties 사용하기. 나중에 숫자 바꾸고 싶은대로 바꾸면 됨
    @Value("${board.page.count}") int PAGECNT;

    // 127.0.0.1:9090/ROOT/board1/insert
    // {"btitle":"aa", "bcontent":"aa"}

    // 내가 아이디를 집어넣는게 아니라 토큰에서 빼오는 방법이 있나? 
    @RequestMapping(value = "/insert", method = {RequestMethod.POST}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> board1InsertPost(
            @ModelAttribute Board1 board1,
            @ModelAttribute Member member,
            @RequestHeader (name = "TOKEN")String token ){
        // 키를 알고 보내야 함. 틀리면 안감. er다이어그램 보면 됨

        System.out.println("BOARDWRITE : "+ token); 

        // String username = jwtUtil.extractUsername(token);
        // System.out.println("USERNAME =>" + username);
        
        Map<String ,Object> map = new HashMap<>();

        // UserDetailsServiceImpl 참고
        UserDetails user =  userDetailsService.loadUserByUsername(member.getMId()); //loadUserByUsername를 수동으로 호출
        System.out.println("MEMBER ID :" + user);
        try{
            if(token !=null) {
                int ret = b1Service.insertBoard1One(board1);
                System.out.println(board1.toString());
                if(ret == 1){
                    map.put("status", 200); // 성공
                }
            }
            map.put("status", 200);
        }
        catch(Exception e){
        e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }

    // 127.0.0.1:9090/ROOT/board1/delete
    // {"bno":3}
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardDeletePost(@RequestBody Board1 board1,
                                            @RequestHeader (name = "TOKEN")String token){
        // 키를 알고 보내야 함. 틀리면 안감. er다이어그램 보면 됨

        Map<String ,Object> map = new HashMap<>();
        try{
            if(token !=null) {
                int ret = b1Service.deleteBoard1One(board1.getBNo());
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
            if(token !=null) {
                int ret = b1Service.updateBoard1One(board1);
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

    // 127.0.0.1:9090/ROOT/board1/selectone?bno=2
    // {"bno":2, "btitle":"222", "bcontent":"222"}
    @RequestMapping(value = "/selectone", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardSelectOneGET(@RequestParam(name = "bNo") long bNo){

        Map<String ,Object> map = new HashMap<>();
        try{
            Board1 board1 = b1Service.selectBoard1One(bNo);
            map.put("status", 200); // 성공 
            map.put("result", board1);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
       
    }

    // 게시판 목록(페이지네이션만 있음, 검색x)
    // 127.0.0.1:9090/ROOT/api/board/selectlist?page=1
    @RequestMapping(value = "/selectlist", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardSelectListGET(@RequestParam(name = "page") int page){

        Map<String ,Object> map = new HashMap<>();
        try{
            // List<Board1> list = b1Service.selectBoard1List(bNo);
            map.put("status", 200); // 성공

        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;


        // List<BoardDTO> list = bMapper.selectBoardList((page * PAGECNT) - (PAGECNT -1), page * PAGECNT);
        // if(list != null){
        //     map.put("status", 200); // 성공
        //     map.put("result", list); 
        // }

    // 페이지네이션                         목록이니까 List
    // @Select({
    //     "SELECT * FROM (",
    //         " SELECT B.*, ROW_NUMBER() OVER (ORDER BY BNO DESC) ROWN ",
    //         " FROM BOARD B ",
    //     " ) WHERE ROWN BETWEEN #{start} AND #{end}"
    // })
    // public List<BoardDTO> selectBoardList(@Param(value = "start") int s, @Param(value = "end") int e);

     
    }

    // 게시물 조회수 1증가 시킴
    // 127.0.0.1:9090/ROOT/board1/updatehit?bno=2
    @RequestMapping(value = "/updatehit", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardUpdateHitGET(
        @RequestParam(name = "bNo") long bNo,
        @RequestHeader (name = "TOKEN")String token){

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

    // 이건 뭐지?
    // // 127.0.0.1:9090/ROOT/api/board/updatehit1?no=2
    //  @RequestMapping(value = "/updatehit1", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
    //                 produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> boardUpdate1PUT(@RequestParam(name = "no") long no){

    //     Map<String ,Object> map = new HashMap<>(); // rest는 화면이 안뜨니까 가져와서 마지막에 return 해주기

    //     // 원래꺼 가져와서 바꿀것만 바꾸고 save 
    //     try{
    //         BoardEntity board = bRepository.findById(no).orElse(null);
    //         board.setHit( board.getHit() + 1L );
    //         bRepository.save(board);
    //         map.put("status", 200);

    //     }
    //     catch(Exception e){
    //         map.put("status", 0);
    //     }
    //     return map;
        
    // }

    // @RequestMapping(value = "/updatehit", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
    //                     produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> boardUpdate1PUT(@RequestParam(name = "bNo") long bNo){
    //     System.out.println(bNo);

    // Map<String ,Object> map = new HashMap<>(); // rest는 화면이 안뜨니까 가져와서 마지막에 return 해주기

    // // 원래꺼 가져와서 바꿀것만 바꾸고 save 
    // try{
    //     Board1 board1 = new Board1();
    //     b1Service.updateBoard1HitOne(bNo);
    //     // Board1 board = b1Service.updateBoard1HitOne(bNo);
    //     // Board1 board1 = bRepository.findById(no).orElse(null);
    //     // board.setHit( board.getHit() + 2L );
    //     b1Repository.save(board1);
    //     map.put("status", 200);

    // }
    // catch(Exception e){
    //     map.put("status", 0);
    // }
    // return map;
    // }
    
}
