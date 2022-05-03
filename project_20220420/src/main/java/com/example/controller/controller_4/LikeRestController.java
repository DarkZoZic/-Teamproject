package com.example.controller.controller_4;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity1.Like;
import com.example.entity.entity1.Member;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.LikeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/like1")
public class LikeRestController {

    @Autowired
    LikeRepository lRepository;

    @Autowired 
    JwtUtil jwtUtil;

    // -- 찜 추가 -- 
    //127.0.0.1:9090/ROOT/like/insert
    @RequestMapping(value = "/insert", 
        method = {RequestMethod.POST},
        consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> InsertPost(
        @RequestBody Like like,
        @RequestHeader (name = "token") String token ) {

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Member memberEntity = new Member();
            memberEntity.setMId(userid);
            System.out.println(memberEntity);

            like.setMember(memberEntity);
            System.out.println(like.toString());

            if(token !=null) {
                lRepository.save(like);
                System.out.println(like.toString());
       
                map.put("status", 200); // 성공
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }

    //127.0.0.1:9090/ROOT/like/insert1
    @RequestMapping(value = "/insert1", 
        method = {RequestMethod.POST},
        consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> InsertPost1(
        @RequestBody Like like,
        @RequestParam(name="btn") String btn,
		@RequestParam(name = "code") long lNo,
		@RequestParam(name = "cnt") long cnt,
        @RequestHeader (name = "token") String token ) {

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Member memberEntity = new Member();
            memberEntity.setMId(userid);
            System.out.println(memberEntity);

            like.setMember(memberEntity);
            System.out.println(like.toString());

            if(token !=null) {
                lRepository.save(like);
                System.out.println(like.toString());
       
                map.put("status", 200); // 성공
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }

    // -- 찜 목록 -- 


    // -- 찜 1개 삭제 -- 

    // -- 찜 일괄 삭제 --


    
}
