package com.example.controller.controller_4;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity1.Member;
import com.example.entity.entity2.CReply;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.CreplyRepository;
import com.example.service.service_4.CreplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/creply")
public class CreplyRestController {

    @Autowired
    CreplyRepository cRepository;

    @Autowired
    CreplyService cService;

    @Autowired
    JwtUtil jwtUtil;

    //127.0.0.1:9090/ROOT/creply/insertreply
    @RequestMapping(value = "/insertreply", 
    method = {RequestMethod.POST},
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> InsertPost(
        @RequestBody CReply cReply,
        @RequestHeader (name = "token") String token ) {

        System.out.println(cReply.toString());

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Member memberEntity = new Member();
            memberEntity.setMId(userid);
            System.out.println(memberEntity);

            cReply.setMember(memberEntity);
            System.out.println(cReply.toString());

            if(token !=null) {
                int ret = cService.insertReplyOne(cReply);
                System.out.println(cReply.toString());
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
    
}
