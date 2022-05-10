package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity1.Steptbl;
import com.example.repository.repository_gibum.StepTblRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/steptbl")
public class SteptblRestController {

    @Autowired StepTblRepository stRepository;

    // 활동내역 추가
	// 127.0.0.1:9090/ROOT/steptbl/insert.json
	//{"sCode":"11", "sContent":"c1" };
	@PostMapping(value = "/insert.json", 
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> JoinPoast(
        @RequestBody Steptbl step){
        System.out.println(step);

    Map<String, Object> map = new HashMap<>();
     try {
        // step.setSCode(step.getSCode());
        // step.setSContent(step.getSContent());
        // System.out.println(step);
        // Steptbl steptbl = new Steptbl();
        System.out.println(step.getScontent());
        System.out.println(step.getScode());
        stRepository.save(step);
        
        
        map.put("status", 200);
        }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
    
}
