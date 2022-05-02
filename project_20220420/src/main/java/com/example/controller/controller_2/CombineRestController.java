package com.example.controller.controller_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity2.ComBine;
import com.example.repository.repository_gibum.CombineViewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/combineview")
public class CombineRestController {

    @Autowired CombineViewRepository cVrepository;

    // 자신의 클럽중 105 조회 (클럽 번호필요)
    // 127.0.0.1:9090/ROOT/combineview/selectnumber.json?no=105
    @GetMapping(value = "/selectnumber.json",
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> CountGet(
        @RequestParam(name = "no")long no,
        @RequestParam(name = "cl")long cl
        ){
            System.out.println(no);
        Map<String, Object> map = new HashMap<>();
        try {
            // List<ComBine> total = cVrepository.selectnumber(no);
            // System.out.println(total);
            map.put("status", 200);
            // map.put("total", total);
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }
    // 127.0.0.1:9090/ROOT/combineview/selectnumber1.json?no=105
    @GetMapping(value = "/selectnumber1.json",
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> Count1Get(
        @RequestParam(name = "no")long no
        ){
            System.out.println(no);
        Map<String, Object> map = new HashMap<>();
        try {
            ComBine combine = cVrepository.findById(no).orElse(null);
            combine  = cVrepository.findTop1ByNoGreaterThanOrderByNoDesc(no);
            // System.out.println(total);
            map.put("status", 200);
            map.put("total", combine);
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }
    
}
