package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity2.Address;
import com.example.repository.repository_gibum.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/address")
public class AddressRestController {
    
    @Autowired AddressRepository addrepository;

    //  카테고리 추가
    // 127.0.0.1:9090/ROOT/address/insert
    @RequestMapping(value = "/insert", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> CategoryPost(
        @RequestBody Address address){
            System.out.println(address);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            
            addrepository.save(address);
        }
         catch (Exception e) {
            e.printStackTrace();
        }

        map.put("status", 200);
    return map;
    }   
}
