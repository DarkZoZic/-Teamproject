package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity1.JoinClub;
import com.example.entity.entity1.Member;
import com.example.entity.entity1.Steptbl;
import com.example.entity.entity2.Club;
import com.example.repository.repository_gibum.JoinClubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/joinclub")
public class JoinClubRestController {
    
    @Autowired JoinClubRepository JCrepository;
    // 회원가입(고객만customer)
	// 127.0.0.1:9090/ROOT/joinclub/insert.json
	//{"mid":"c1", "mpw":"c1" };
	@RequestMapping(value = "/insert.json", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
public Map<String, Object> JoinPost(
    @ModelAttribute JoinClub joinclub){
    System.out.println(joinclub);
Map<String, Object> map = new HashMap<>();
try {
    Member member = new Member();
    member.setMId((String) joinclub.getMember().getMId());
    joinclub.setMember(member);
    System.out.println("========="+joinclub);    

    Club club = new Club();
    club.setCNo((Long) joinclub.getClub().getCNo());
    joinclub.setClub(club);;
    System.out.println(club);

    Steptbl steptbl = new Steptbl();
    steptbl.setSCode((Long) joinclub.getSteptbl().getSCode());
    joinclub.setSteptbl(steptbl);
    System.out.println(steptbl);

    JCrepository.save(joinclub);
    map.put("status", 200);
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
}
