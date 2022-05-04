package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity2.Category;
import com.example.repository.repository_gibum.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cate")
public class CateRestController {

    @Autowired CategoryRepository ctRepository;
    //  카테고리 추가
    // 127.0.0.1:9090/ROOT/cate/insert
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
    // 127.0.0.1:9090/ROOT/cate/search
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
}
