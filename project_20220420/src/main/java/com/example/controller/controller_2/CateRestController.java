package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity2.Category;
import com.example.entity.entity2.Combineaddr;
import com.example.repository.repository_gibum.CategoryRepository;
import com.example.repository.repository_gibum.CombineaddrViewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.lettuce.core.dynamic.annotation.Param;

@RestController
@RequestMapping(value = "/cate")
public class CateRestController {

    @Autowired CategoryRepository ctRepository;

    @Autowired CombineaddrViewRepository cbRepository;



    //  클럽검색 ex운동
    // 127.0.0.1:9090/cluver/cate/searchcate
    @RequestMapping(value = "/searchcate", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> searchcateGet(
        @Param(value = "cate")String cate
    ){
        System.out.println(cate);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            List<Combineaddr> combineaddr = cbRepository.CateSearch(cate);

            
                map.put("status", 200);
                map.put("result", combineaddr);
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    return map;
    }   
    //  클럽검색 ex운동)축구
    // 127.0.0.1:9090/cluver/cate/searchcate2
    @RequestMapping(value = "/searchcate2", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> searchcate2Get(
        @Param(value = "cate")String cate
    ){
        System.out.println(cate);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            List<Combineaddr> combineaddr = cbRepository.CateSearch2(cate);

            
                map.put("status", 200);
                map.put("result", combineaddr);
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    return map;
    }   

    //  중분류로 클럽검색 ex) 탁구
    // 127.0.0.1:9090/cluver/cate/searchclub2
    @RequestMapping(value = "/searchclub2", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> SearchClub2Get(
        @RequestParam(value = "cate") String cate){
            System.out.println(cate);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            List<Combineaddr> addr = cbRepository.CateSearch2(cate);
            System.out.println(addr);
            // System.out.println(addr.getA2());


            // List<Combineaddr> combine = cbRepository.addressesList2(addr.getA1());
            // System.out.println(combine);
            map.put("status", 200);
            map.put("result", addr);
            
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    return map;
    }
    //  대분류로 클럽검색 ex) 운동
    // 127.0.0.1:9090/cluver/cate/searchclub
    @RequestMapping(value = "/searchclub", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> SearchClubGet(
        @RequestParam(value = "cate") String cate){
            System.out.println(cate);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            List<Combineaddr> addr = cbRepository.CateSearch(cate);
            System.out.println(addr);
            // System.out.println(addr.getA2());


            // List<Combineaddr> combine = cbRepository.addressesList2(addr.getA1());
            // System.out.println(combine);
            map.put("status", 200);
            map.put("result", addr);
            
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    return map;
    }

    
    //  카테고리 추가
    // 127.0.0.1:9090/cluver/cate/insert
    @RequestMapping(value = "/insert", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> CategoryPost(
        @RequestBody Category category){
            System.out.println(category);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            
            ctRepository.save(category);
        }
         catch (Exception e) {
            e.printStackTrace();
        }

        map.put("status", 200);
    return map;
    }   
    //  중분류 클릭시 소분류 조회
    // 127.0.0.1:9090/cluver/cate/search
    @RequestMapping(value = "/search", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> boardPost(
        @RequestParam(value = "cate") String cate){
        System.out.println(cate);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            
            List<Category> category = ctRepository.cate1lList(cate);
            System.out.println(category);
            // ctRepository.save(category);
            map.put("status", 200);
            map.put("result",  category);
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    return map;
    }   
    //  컬럼 2개입력시 번호가져오기
    // 127.0.0.1:9090/cluver/cate/catetwo
    @RequestMapping(value = "/catetwo", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> CatetwoGet(
        @Param(value = "cgcate1") String cate1,
        @Param(value = "cgcate2") String cate2
    ){
        System.out.println(cate1);
        System.out.println(cate2);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            Category cate = ctRepository.findByCgcate1AndCgcate2(cate1,cate2);
            
            map.put("status", 200);
            map.put("result", cate);
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    return map;
    }   
    //  대분류 조회
    // 127.0.0.1:9090/cluver/cate/catelist1
    @RequestMapping(value = "/catelist1", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> Cate1listGet(){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            
            List<Category> category = ctRepository.findAll();
            System.out.println(category);
            // ctRepository.save(category);
            map.put("status", 200);
            map.put("result",  category);
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    return map;
    }   
}
