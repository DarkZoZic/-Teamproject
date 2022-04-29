package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity1.Steptbl;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/steptbl")
public class SteptblRestController {

    // 회원가입(고객만customer)
	// 127.0.0.1:9090/ROOT/member/join.json
	//{"mid":"c1", "mpw":"c1" };
	@RequestMapping(value = "/steptbl.json", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
public Map<String, Object> JoinPost(
    @RequestBody Steptbl step){

Map<String, Object> map = new HashMap<>();
try {
   
    
    
    map.put("status", 200);
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
    
}
