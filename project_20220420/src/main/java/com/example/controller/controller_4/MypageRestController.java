package com.example.controller.controller_4;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity1.Member;
import com.example.service.service_4.MypageService;

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
@RequestMapping(value = "/mypage")
public class MypageRestController {

    @Autowired
    MypageService mService;

    // 127.0.0.1:9090/ROOT/mypage/pwupdate
    @RequestMapping(value = "/pwupdate", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardUpdatePost(
            @RequestBody Member member,
            @RequestHeader (name = "TOKEN")String token ) {

        Map<String ,Object> map = new HashMap<>();
        try{
            if(token !=null) {
                Member member1 = mService.selectMemberOne( member.getMId() );
                member1.setMPw( member.getMPw() );
                
                // mService.updateMember(member);
               
                mService.updatePassword(member);
                map.put("status", 200); // 성공
                
            }   
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }
}
