package com.example.controller.controller_4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.Qna;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.QnaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/qna")
public class QnaRestController {

    @Autowired
    QnaRepository qRepository;
    
    @Autowired 
    JwtUtil jwtUtil;

    // int PAGECNT = 10
    // global.properties 사용하기. 나중에 숫자 바꾸고 싶은대로 바꾸면 됨
    @Value("${board.page.count}") int PAGECNT;

    //127.0.0.1:9090/ROOT/qna/insert
    @RequestMapping(value = "/insert", 
        method = {RequestMethod.POST},
        consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> InsertPost(
        @ModelAttribute Qna qna,
        @RequestHeader (name = "token") String token ) {

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Member memberEntity = new Member();
            memberEntity.setMid(userid);
            System.out.println(memberEntity);

            qna.setMember(memberEntity);
            System.out.println(qna.toString());

            if(token !=null) {
                qRepository.save(qna);
                                
                map.put("status", 200); // 성공
                map.put("result", "등록완료");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }

    // 1개조회
    // 127.0.0.1:9090/ROOT/qna/selectone?qno=2
    @RequestMapping(value = "/selectone", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardSelectOneGET(
        @RequestParam(name = "qno") long qno,
        @RequestHeader (name = "token") String token ){

        Map<String ,Object> map = new HashMap<>();
        try{
            if(token != null){
                Qna qna = qRepository.findById(qno).orElse(null);
                if(qna != null){
                    map.put("status", 200); // 성공 
                    map.put("result", qna);
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
    // 127.0.0.1:9090/ROOT/qna/selectlist?page=1
    @RequestMapping(value = "/selectlist", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardSelectListGET(
        @RequestParam(name = "page") int page ){

        Map<String ,Object> map = new HashMap<>();
        try{
            PageRequest pageRequest = PageRequest.of(page-1, PAGECNT);
            List<Qna> qList = qRepository.findAllByOrderByQnoDesc(pageRequest);
            // System.out.println(qList);
            map.put("status", 200); // 성공
            map.put("result", qList);

        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    } 

    // 검색 + 페이지네이션
    // 127.0.0.1:9090/ROOT/qna/search
    @RequestMapping(value = "/search", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> searchGET(
        @RequestParam(name = "page") int page,
        @RequestParam(name = "btn") String btn,
        @RequestParam(name = "qtitle", defaultValue = "") String qtitle,
        @RequestParam(name = "qcontent", defaultValue = "") String qcontent,
        @RequestParam(name = "mid", defaultValue = "") String mid 
        ){

        // System.out.println(qtitle);

        Map<String ,Object> map = new HashMap<>();
        try{
            if(btn.equals("제목")){
                // 제목
                PageRequest pageRequest = PageRequest.of(page-1, PAGECNT);
                List<Qna> qList = qRepository.findByQtitleContainingOrderByQnoDesc(qtitle, pageRequest);
                map.put("status", 200); // 성공
                map.put("result", qList);
            }

            else if(btn.equals("내용")){
                // 내용
                PageRequest pageRequest1 = PageRequest.of(page-1, PAGECNT);
                List<Qna> q1List = qRepository.findByQcontentContainingOrderByQnoDesc(qcontent, pageRequest1);
                // System.out.println(q1List);
                map.put("status", 200); // 성공
                map.put("result", q1List);
            }

            else if(btn.equals("글쓴이")){
                // 글쓴이
                PageRequest pageRequest2 = PageRequest.of(page-1, PAGECNT);
                List<Qna> q2List = qRepository.findByMember_midContainingOrderByQnoDesc(mid, pageRequest2);
                // System.out.println(q2List);
                map.put("status", 200); // 성공
                map.put("result", q2List);
            }

            else if(btn.equals("전체")){
                // 전체
                PageRequest pageRequest3 = PageRequest.of(page-1, PAGECNT);
                List<Qna> q3List = qRepository.findByQcontentOrMember_midOrQtitleContainingOrderByQnoDesc(qtitle, qcontent, mid, pageRequest3);
                // System.out.println(q3List);
                map.put("status", 200); // 성공
                map.put("result", q3List);
            }

        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }
    
}
