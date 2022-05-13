package com.example.controller.controller_4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.Qna;
import com.example.entity.entity2.Board1;
import com.example.entity.entity2.CReply;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.CreplyRepository;
import com.example.service.service_4.CreplyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/creply")
public class CreplyRestController {

    @Autowired
    CreplyRepository cRepository;

    @Autowired
    CreplyService cService;

    @Autowired
    JwtUtil jwtUtil;

    //127.0.0.1:9090/ROOT/api/creply/board_insert
    @RequestMapping(value = "/board_insert", 
    method = {RequestMethod.POST},
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> BInsertPost(
        @ModelAttribute CReply cReply,
        @RequestHeader (name = "token") String token ) {

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Member memberEntity = new Member();
            memberEntity.setMid(userid);
            System.out.println(memberEntity);

            cReply.setMember(memberEntity);
            System.out.println(cReply.toString());
            
            Board1 board1Entity = new Board1();
            board1Entity.setBno(cReply.getBoard1().getBno());

            if(token !=null) {
                cReply.setBoard1(board1Entity);
                cReply.setRecontent(cReply.getRecontent());
                cReply.setReprivate(cReply.getReprivate());
                // cReply.setReparentnumber(cReply.getReparentnumber());

                cRepository.save(cReply);
                map.put("status", 200); // 성공
            }
            System.out.println("=============="+cReply.toString());
            map.put("status", 200);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }

    // 127.0.0.1:9090/ROOT/api/creply/board_delete
    // {"bno":3}
    @RequestMapping(value = "/board_delete", method = {RequestMethod.DELETE}, consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> BDeletePost(
        @RequestBody CReply cReply,
        @RequestHeader (name = "token")String token ){
        // 키를 알고 보내야 함. 틀리면 안감. er다이어그램 보면 됨

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            CReply cReply1 = cRepository.getById(cReply.getRenumber());
            System.out.println(cReply1.toString());

            System.out.println("번호"+cReply.getRenumber());

            if(userid.equals( cReply1.getMember().getMid() )){
                // Board1 result = b1Service.selectBoard1One(board.getBNo());

                // 삭제
                cRepository.deleteById(cReply1.getRenumber());
                map.put("status", 200); // 성공

                
            }
            else if (!userid.equals( cReply1.getMember().getMid() )){
                map.put("status", 0); 
            }
           
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", -1); // 실패
        }
        return map;
    }

    // 127.0.0.1:9090/ROOT/api/creply/board_update
    // 제목, 내용, 번호
    // {"bno":2, "btitle":"222", "bcontent":"222"}
    @RequestMapping(value = "/board_update", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> BUpdatePost(
            @ModelAttribute CReply cReply, 
            @RequestHeader (name = "TOKEN")String token ) {

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            CReply cReply1 = cRepository.getById(cReply.getRenumber());
            System.out.println(cReply1.toString());

            System.out.println("번호"+cReply.getRenumber());

            if(userid.equals( cReply1.getMember().getMid() )){
                // Board1 result = b1Service.selectBoard1One(board.getBNo());
                CReply result = cRepository.getById(cReply1.getRenumber());

                // 수정
                result.setRecontent(cReply.getRecontent());
                result.setReprivate(cReply.getReprivate());

                cRepository.save(result);
                
                map.put("status", 200); // 성공
                
            }
            else if (!userid.equals( cReply1.getMember().getMid() )){
                map.put("status", 0); 
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", -1); // 실패
        }
        return map;
    }

    // 원본 글번호가 일치하는 댓글 조회
    // 127.0.0.1:9090/ROOT/api/creply/board_selectone
    @RequestMapping(value = "/board_selectone", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> BSelectOneGET(
        @RequestParam(name = "bno") Long bno,
        @RequestHeader (name = "token") String token){

        Map<String ,Object> map = new HashMap<>();

        try{
            if(token != null){

                List<CReply> cReply = cRepository.findByBoard1_bnoOrderByRenumberAsc(bno);
                map.put("result",cReply);
                map.put("status",200);

            }

        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;

    }

    // =======================================================================================

    //127.0.0.1:9090/ROOT/api/creply/qna_insert
    @RequestMapping(value = "/qna_insert", 
    method = {RequestMethod.POST},
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> QInsertPost(
        @ModelAttribute CReply cReply,
        @RequestHeader (name = "token") String token ) {

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Member memberEntity = new Member();
            memberEntity.setMid(userid);
            System.out.println(memberEntity);

            cReply.setMember(memberEntity);
            System.out.println(cReply.toString());

            Qna qnaEntity = new Qna();
            qnaEntity.setQno(cReply.getQna().getQno());
            
            if(token !=null) {
                cReply.setQna(qnaEntity);
                cReply.setRecontent(cReply.getRecontent());
                cReply.setReprivate(cReply.getReprivate());
                // cReply.setReparentnumber(cReply.getReparentnumber());

                cRepository.save(cReply);
                map.put("status", 200); // 성공
            }
            System.out.println("=============="+cReply.toString());
            map.put("status", 200);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }

    // 127.0.0.1:9090/ROOT/api/creply/qna_delete
    // {"bno":3}
    @RequestMapping(value = "/qna_delete", method = {RequestMethod.DELETE}, consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> QDeletePost(
        @RequestBody CReply cReply,
        @RequestHeader (name = "token")String token ){
        // 키를 알고 보내야 함. 틀리면 안감. er다이어그램 보면 됨

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            CReply cReply1 = cRepository.getById(cReply.getRenumber());
            System.out.println(cReply1.toString());

            System.out.println("번호"+cReply.getRenumber());

            if(userid.equals( cReply1.getMember().getMid() )){
    
                // 삭제
                cRepository.deleteById(cReply1.getRenumber());
                map.put("status", 200); // 성공

                
            }
            else if (!userid.equals( cReply1.getMember().getMid() )){
                map.put("status", 0); 
            }
           
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", -1); // 실패
        }
        return map;
    }
 
    // 127.0.0.1:9090/ROOT/api/creply/qna_update
    // 제목, 내용, 번호
    // {"bno":2, "btitle":"222", "bcontent":"222"}
    @RequestMapping(value = "/qna_update", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> QUpdatePost(
            @ModelAttribute CReply cReply, 
            @RequestHeader (name = "TOKEN")String token ) {

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            CReply cReply1 = cRepository.getById(cReply.getRenumber());
            System.out.println(cReply1.toString());

            System.out.println("번호"+cReply.getRenumber());

            if(userid.equals( cReply1.getMember().getMid() )){
                // Board1 result = b1Service.selectBoard1One(board.getBNo());
                CReply result = cRepository.getById(cReply1.getRenumber());

                // 수정
                result.setRecontent(cReply.getRecontent());
                result.setReprivate(cReply.getReprivate());

                cRepository.save(result);
                
                map.put("status", 200); // 성공
                
            }
            else if (!userid.equals( cReply1.getMember().getMid() )){
                map.put("status", 0); 
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", -1); // 실패
        }
        return map;
    }

    // 원본 글번호가 일치하는 댓글 조회
    // 127.0.0.1:9090/ROOT/api/creply/qna_selectone
    @RequestMapping(value = "/qna_selectone", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> QSelectOneGET(
        @RequestParam(name = "qno") Long qno,
        @RequestHeader (name = "token") String token){

        Map<String ,Object> map = new HashMap<>();

        try{
            if(token != null){

                List<CReply> cReply = cRepository.findByQna_qnoOrderByRenumberAsc(qno);
                map.put("result",cReply);
                map.put("status",200);

            }

        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;

    }




}
