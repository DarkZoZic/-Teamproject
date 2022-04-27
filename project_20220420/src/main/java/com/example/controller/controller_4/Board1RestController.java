package com.example.controller.controller_4;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity2.Board1;
import com.example.repository.repository_4.Board1Repository;
import com.example.service.service_4.Board1Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restboard1")
public class Board1RestController {

    @Autowired Board1Repository b1Repository;
    @Autowired Board1Service b1Service;

    @RequestMapping(value = "/updatehit", method = {RequestMethod.PUT}, consumes = {MediaType.ALL_VALUE},
                        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardUpdate1PUT(@RequestParam(name = "bNo") long bNo){

    Map<String ,Object> map = new HashMap<>(); // rest는 화면이 안뜨니까 가져와서 마지막에 return 해주기

    // 원래꺼 가져와서 바꿀것만 바꾸고 save 
    try{
        Board1 board1 = new Board1();
        b1Service.updateBoard1HitOne(bNo);
        // Board1 board = b1Service.updateBoard1HitOne(bNo);
        // Board1 board1 = bRepository.findById(no).orElse(null);
        // board.setHit( board.getHit() + 2L );
        b1Repository.save(board1);
        map.put("status", 200);

    }
    catch(Exception e){
        map.put("status", 0);
    }
    return map;
    }
    
}
