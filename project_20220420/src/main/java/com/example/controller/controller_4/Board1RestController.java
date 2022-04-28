package com.example.controller.controller_4;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.entity.entity2.Board1;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.Board1Repository;
import com.example.service.service_4.Board1Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/board1")
public class Board1RestController {

    @Autowired Board1Repository b1Repository;
    @Autowired Board1Service b1Service;
    @Autowired JwtUtil jwtUtil;


    // int PAGECNT = 10
    // global.properties 사용하기. 나중에 숫자 바꾸고 싶은대로 바꾸면 됨
    @Value("${board.page.count}") int PAGECNT;

    // 127.0.0.1:9090/ROOT/api/board1/insert
    // {"btitle":"aa", "bcontent":"aa"}
    @RequestMapping(value = "/insert", method = {RequestMethod.POST}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> board1InsertPost(
            @RequestBody Board1 board1,
            @RequestHeader (name = "TOKEN")String token ){
        // 키를 알고 보내야 함. 틀리면 안감. er다이어그램 보면 됨

        System.out.println("BOARDWRITE : "+ token); 

        String username = jwtUtil.extractUsername(token);
        System.out.println(username);
        
        
        Map<String ,Object> map = new HashMap<>();
        try{
            if(token !=null) {
                int ret = b1Service.insertBoard1One(board1);
                System.out.println(board1.toString());
                if(ret == 1){
                    map.put("status", 200); // 성공
                }
            }
            
        }
        catch(Exception e){
            map.put("status", 0); // 실패
        }
        return map;
    }

    // // 127.0.0.1:9090/ROOT/api/board/delete
    // // {"bno":3}
    // @RequestMapping(value = "/delete", method = {RequestMethod.DELETE}, consumes = {MediaType.ALL_VALUE},
    //                 produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> boardDeletePost(@RequestParam(name = "bno") long bno){
    //     // 키를 알고 보내야 함. 틀리면 안감. er다이어그램 보면 됨

    //     Map<String ,Object> map = new HashMap<>();

    //     map.put("status", 0); // 실패

    //     int ret = bMapper.deleteBoardOne(bno);
    //     if(ret == 1){
    //         map.put("status", 200); // 성공
    //     }

    //     return map;
    // }

    // // 127.0.0.1:9090/ROOT/api/board/update
    // // 제목, 내용, 번호
    // // {"bno":2, "btitle":"222", "bcontent":"222"}
    // @RequestMapping(value = "/update", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
    //                 produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> boardUpdatePost(@RequestBody Board1 board1){

    //     Map<String ,Object> map = new HashMap<>();

    //     map.put("status", 0); // 실패

    //     int ret = bMapper.updateBoardOne(board);
    //     if(ret == 1){
    //         map.put("status", 200); // 성공
    //     }

    //     return map;
    // }

    // // 127.0.0.1:9090/ROOT/api/board/selectone?bno=2
    // // {"bno":2, "btitle":"222", "bcontent":"222"}
    // @RequestMapping(value = "/selectone", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
    //                 produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> boardSelectOneGET(@RequestParam(name = "bno") long bno){

    //     Map<String ,Object> map = new HashMap<>();

    //     map.put("status", 0); // 실패

    //     BoardDTO retBoard = bMapper.selectBoardOne(bno);
    //     if(retBoard != null){
    //         map.put("status", 200); // 성공
    //         map.put("result", retBoard); 
    //     }

    //     return map;
    // }

    // // 게시판 목록(페이지네이션만 있음, 검색x)
    // // 127.0.0.1:9090/ROOT/api/board/selectlist?page=1
    // @RequestMapping(value = "/selectlist", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
    //                 produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> boardSelectListGET(@RequestParam(name = "page") int page){

    //     Map<String ,Object> map = new HashMap<>();

    //     map.put("status", 0); // 실패

       
    //     List<BoardDTO> list = bMapper.selectBoardList((page * PAGECNT) - (PAGECNT -1), page * PAGECNT);
    //     if(list != null){
    //         map.put("status", 200); // 성공
    //         map.put("result", list); 
    //     }

    //     return map;
    // }

    // // 게시물 조회수 1증가 시킴
    // // 127.0.0.1:9090/ROOT/api/board/updatehit?bno=2
    // @RequestMapping(value = "/updatehit", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
    //                 produces = {MediaType.APPLICATION_JSON_VALUE})
    // public Map<String, Object> boardUpdateHitGET(@RequestParam(name = "bno") long bno){

    //     Map<String ,Object> map = new HashMap<>();

    //     map.put("status", 0); // 실패

    //     int ret = bMapper.updateBoardHitOne(bno);
    //     if(ret == 1){
    //         map.put("status", 200); // 성공
    //     }

    //     return map;
    // }

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
