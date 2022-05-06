package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity2.Address;
import com.example.entity.entity2.Club;
import com.example.entity.entity2.Combineaddr;
import com.example.repository.repository_gibum.AddressRepository;
import com.example.repository.repository_gibum.ClubRepository;
import com.example.repository.repository_gibum.CombineaddrView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/address")
public class AddressRestController {
    
    @Autowired AddressRepository addrepository;

    @Autowired ClubRepository cRepository;

    @Autowired CombineaddrView cbRepository;


    //  대분류 검색으로 클럽찾기 ex) ~~시
    // 127.0.0.1:9090/ROOT/address/searchclub
    @RequestMapping(value = "/searchclub", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> AddressClub1Get(
        @RequestParam(value = "no") Long no){
            System.out.println(no);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            Address addr = addrepository.findByaCode(no);
            System.out.println(addr);
            System.out.println(addr.getA1());


            List<Combineaddr> combine = cbRepository.addressesList2(addr.getA1());
            System.out.println(combine);
            map.put("status", 200);
            map.put("result", combine);
            
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    return map;
    }   
    //  중분류 검색 ex) 진구
    // 127.0.0.1:9090/ROOT/address/searchclub2
    @RequestMapping(value = "/searchclub2", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> AddressClub2Get(
        @RequestParam(value = "no") Long no){
            System.out.println(no);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            Address addr = addrepository.findByaCode(no);
            System.out.println(addr);
            System.out.println(addr.getA2());


            List<Combineaddr> combine = cbRepository.addressesList3(addr.getA2());
            System.out.println(combine);
            map.put("status", 200);
            map.put("result", combine);
            
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    return map;
    }   
    //  대분류 검색 ex) 부산시
    // 127.0.0.1:9090/ROOT/address/search1
    @RequestMapping(value = "/search1", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> address1Get(
        @RequestParam(value = "address") String address){
            System.out.println(address);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            List<Address> list = addrepository.addressesList1(address);
            System.out.println(list);
            
            // addrepository.save(address);
            if(list != null) {
                map.put("status", 200);
                map.put("result", list);
            }
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    return map;
    }   
    //  주소 추가
    // 127.0.0.1:9090/ROOT/address/insert
    @RequestMapping(value = "/insert", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> AddressPost(
        @RequestBody Address address){
            System.out.println(address);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            
            addrepository.save(address);
            map.put("status", 200);
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    return map;
    }   
}
