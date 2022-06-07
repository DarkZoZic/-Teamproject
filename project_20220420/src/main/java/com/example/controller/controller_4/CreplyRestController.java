package com.example.controller.controller_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.MemberPersonal;
import com.example.entity.entity1.Qna;
import com.example.entity.entity2.Board1;
import com.example.entity.entity2.CReply;
import com.example.entity.entity2.CReplyMemberView;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_3.CReplyMemberViewRepository;
import com.example.repository.repository_3.PersonalMemberRepository;
import com.example.repository.repository_4.CreplyRepository;
import com.example.service.service_4.CreplyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
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
    PersonalMemberRepository pmRep;
    
    @Autowired
    CReplyMemberViewRepository crmvRep;

    @Autowired
    JwtUtil jwtUtil;

    // int PAGECNT = 10
    // global.properties 사용하기. 나중에 숫자 바꾸고 싶은대로 바꾸면 됨
    @Value("${board.page.count}") int PAGECNT;

    // 댓글 작성
    //127.0.0.1:9090/cluver/api/creply/board_insert
    @RequestMapping(value = "/board_insert", 
    method = {RequestMethod.POST},
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> BInsertPost(
        @RequestBody CReply cReply,
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
            System.out.println("BNO==========>"+cReply.getBoard1().getBno());

            if(token !=null) {
                cReply.setBoard1(board1Entity);
                cReply.setRecontent(cReply.getRecontent());
                cReply.setReprivate(cReply.getReprivate());

                cRepository.save(cReply);
                cReply.setReparentnumber(cReply.getRenumber());
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

    // 답댓글 작성 
    @RequestMapping(value = "/reboard_insert", 
    method = {RequestMethod.POST},
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> BreInsertPost(
        @RequestBody CReply cReply,
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
            System.out.println("BNO==========>"+cReply.getBoard1().getBno());

            if(token !=null) {
                cReply.setBoard1(board1Entity);
                cReply.setRerecontent(cReply.getRerecontent());
                cReply.setReprivate(cReply.getReprivate());
                cReply.setReparentnumber(cReply.getReparentnumber());

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


    // 127.0.0.1:9090/cluver/api/creply/board_delete
    // {"bno":3}
    @RequestMapping(value = "/board_delete", method = {RequestMethod.DELETE}, consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> BDeletePost(
        @RequestParam(name = "renumber") Long renumber,
        @RequestHeader (name = "token")String token ){
        // 키를 알고 보내야 함. 틀리면 안감. er다이어그램 보면 됨

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            cRepository.deleteByMember_midAndRenumber(userid, renumber);
            map.put("status", 200); // 성공

        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }

    // 127.0.0.1:9090/cluver/api/creply/board_update
    // 제목, 내용, 번호
    // {"bno":2, "btitle":"222", "bcontent":"222"}
    @RequestMapping(value = "/board_update", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> BUpdatePost(
            @RequestBody CReply cReply, 
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
                // result.setReupdatedate(cReply.getReupdatedate());

                cRepository.save(result);
                System.out.println("댓글수정결과==========="+result.getRerecontent());
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
    // 127.0.0.1:9090/cluver/api/creply/board_selectone
    @RequestMapping(value = "/board_selectone", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> BSelectOneGET(
        @RequestParam(name = "bno") Long bno,
        @RequestHeader (name = "token") String token){

        Map<String ,Object> map = new HashMap<>();

        try{
            if(token != null){

                // List<CReply> cReply = cRepository.findByBoard1_bnoOrderByRenumberAsc(bno);
                List<CReply> cReply = cRepository.findByBoard1_bnoOrderByReparentnumberAscRenumberAsc(bno);
                
                List<CReplyMemberView> mlist = new ArrayList<>(); // 댓글 작성자 닉네임 리스트
				for(int i=0; i<cReply.size(); i++)
				{
					CReplyMemberView mp = crmvRep.findByRenumber(cReply.get(i).getRenumber());
					mlist.add(mp);
				}
				System.out.println(mlist);
                long total = cRepository.countByBoard1_bno(bno);
                map.put("result",cReply);
                map.put("replynicklist", mlist);
                map.put("status",200);
                map.put("total",total);
            }

        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;

    }

    // =======================================================================================

    //127.0.0.1:9090/cluver/api/creply/qna_insert
    @RequestMapping(value = "/qna_insert", 
    method = {RequestMethod.POST},
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> QInsertPost(
        @RequestBody CReply cReply,
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

                cRepository.save(cReply);
                cReply.setReparentnumber(cReply.getRenumber());
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

    // 127.0.0.1:9090/cluver/api/creply/qna_delete
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
 
    // 127.0.0.1:9090/cluver/api/creply/qna_update
    // 제목, 내용, 번호
    // {"bno":2, "btitle":"222", "bcontent":"222"}
    @RequestMapping(value = "/qna_update", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> QUpdatePost(
            @RequestBody CReply cReply, 
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
    // 127.0.0.1:9090/cluver/api/creply/qna_selectone
    @RequestMapping(value = "/qna_selectone", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> QSelectOneGET(
        @RequestParam(name = "qno") Long qno,
        @RequestHeader (name = "token") String token){

        Map<String ,Object> map = new HashMap<>();

        try{
            if(token != null){

                // List<CReply> cReply = cRepository.findByQna_qnoOrderByRenumberAsc(qno);
                // List<CReply> cReply = cRepository.findByQna_qnoOrderByReparentnumberAsc(qno);
                List<CReply> cReply = cRepository.findByQna_qnoOrderByReparentnumberAscRenumberAsc(qno);
                
                List<CReplyMemberView> mlist = new ArrayList<>(); // 댓글 작성자 닉네임 리스트
				for(int i=0; i<cReply.size(); i++)
				{
					CReplyMemberView mp = crmvRep.findByRenumber(cReply.get(i).getRenumber());
					mlist.add(mp);
				}
				System.out.println(mlist);
                long total = cRepository.countByQna_qno(qno);             
                
                map.put("result",cReply);
                map.put("replynicklist", mlist);
                map.put("status",200);
                map.put("total",total);

            }

        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;

    }

    // 댓글 작성자와 토큰의 아이디가 일치하는 댓글 조회
    // 127.0.0.1:9090/cluver/api/creply/selectreply
    @RequestMapping(value = "/selectreply", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> selectBoardGET(
        @RequestParam(name = "page") int page, 
        @RequestHeader (name = "token") String token ){

        Map<String ,Object> map = new HashMap<>();
        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            PageRequest pageRequest = PageRequest.of(page-1, PAGECNT);
            long total = cRepository.countByMember_mid(userid);

            List<CReply> cList = cRepository.findByMember_midOrderByRenumberDesc(userid, pageRequest);
            // System.out.println(qList);
            map.put("status", 200); // 성공
            map.put("result", cList);
            map.put("result1", total);

        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    } 

    // 일괄삭제
    // 127.0.0.1:9090/cluver/api/creply/deletebatch
    @RequestMapping(value = "/deletebatch", method = {RequestMethod.POST}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> deleteBatch(
        @RequestParam(name = "renumber") Long[] renumber,
        @RequestHeader (name = "token")String token ){

        Map<String ,Object> map = new HashMap<>();
        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            cRepository.deleteByMember_midAndRenumberIn(userid, renumber);
            map.put("status", 200); // 성공
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }






}
