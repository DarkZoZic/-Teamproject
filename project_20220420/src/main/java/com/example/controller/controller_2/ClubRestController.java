package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity2.Address;
import com.example.entity.entity2.Category;
import com.example.entity.entity2.Club;
import com.example.repository.repository_gibum.ClubRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired ClubRepository cRepository;
    // 클럽생성
	// 127.0.0.1:9090/ROOT/club/insert.json
	//{"mid":"c1", "mpw":"c1" };
	@RequestMapping(value = "/insert.json", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
public Map<String, Object> JoinClubpost(
    @ModelAttribute Club club,
    @RequestParam(name = "file",required = false) MultipartFile file){
    System.out.println(club);
Map<String, Object> map = new HashMap<>();
try {
    System.out.println(file);
    if(
    file != null){
    if(!file.isEmpty()){
        club.setCThumbnail(file.getBytes());
        club.setCImagesize(file.getSize());
        club.setCImagetype(file.getContentType());
        club.setCImagename(file.getOriginalFilename());
        
    }
}
    Address address = new Address();
    address.setACode((Long) club.getAddress().getACode());

    Category category = new Category();
    category.setCgCode((Long) club.getCategory().getCgCode());
    
    cRepository.save(club);
    map.put("status", 200);
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
    
}
