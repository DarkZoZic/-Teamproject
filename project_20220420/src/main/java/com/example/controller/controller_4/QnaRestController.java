package com.example.controller.controller_4;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.Qna;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.QnaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
}
