package com.example.controller.controller_4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity2.ClubBoard;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.clubboardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cboard")
public class clubboardRestController {

    @Autowired
    clubboardRepository cbRepository;

    @Autowired 
    JwtUtil jwtUtil;

    // int PAGECNT = 10
    // global.properties 사용하기. 나중에 숫자 바꾸고 싶은대로 바꾸면 됨
    @Value("${board.page.count}") int PAGECNT;

    // 게시글 작성자와 토큰의 아이디가 일치하는 글만 조회
    // 127.0.0.1:9090/ROOT/api/cboard/selectboard
    @RequestMapping(value = "/selectboard", method = {RequestMethod.GET}, consumes = {MediaType.ALL_VALUE},
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
            long total = cbRepository.countByMember_mid(userid);

            List<ClubBoard> cbList = cbRepository.findByMember_midOrderByCbnoDesc(userid, pageRequest);
            map.put("status", 200); // 성공
            map.put("result", cbList);
            map.put("result1", total);

        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    } 
    
}
