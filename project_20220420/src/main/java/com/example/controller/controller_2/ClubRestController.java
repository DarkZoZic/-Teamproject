package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity2.Club;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/club")
public class ClubRestController {

    // 회원가입(고객만customer)
	// 127.0.0.1:9090/ROOT/member/join.json
	//{"mid":"c1", "mpw":"c1" };
	@RequestMapping(value = "/insert.json", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
public Map<String, Object> JoinPost(
    @ModelAttribute Club club,
    @RequestParam(name = "file",required = false) MultipartFile file){
    System.out.println(club.toString());
BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

Map<String, Object> map = new HashMap<>();
try {
//     if(
//     file != null){
//     if(!file.isEmpty()){
//         club.setCThumbnail(cThumbnail);
//         club.setMImagesize(file.getSize());
//         club.setMImagetype(file.getContentType());
//         club.setMImagename(file.getOriginalFilename());
        
//     }
// }

//     member.setMPw(bcpe.encode( member.getMPw()) );
//     mRepository.save(member);
    // mService.memberInsert(member);
    map.put("status", 200);
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
    
}
