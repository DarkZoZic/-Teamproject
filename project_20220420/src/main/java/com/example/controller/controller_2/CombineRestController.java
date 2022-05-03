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

    // 자신의 클럽중 신청내역 조회 (클럽 번호필요) 
    // 마스터, 관리자권한 있어야함 101 or 102
    // 127.0.0.1:9090/ROOT/combineview/selectnumber.json?no=9
    @GetMapping(value = "/selectnumber.json",
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> CountGet(
        @RequestParam(name = "no")long no){
                            // no = c_no
        System.out.println(no);
        Map<String, Object> map = new HashMap<>();
        try {
            List<ComBine> total = cVrepository.selectnumber(no);
            System.out.println(total);
            map.put("status", 200);
            map.put("total", total);
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }
    // 아이디를 받아서 자신의 클럽신청내역 조회
    // 처리결과포함(승인, 거절)
    // 127.0.0.1:9090/ROOT/combineview/appdetails.json?id=b
    @GetMapping(value = "/appdetails.json",
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> appDetailsGet(
        @RequestParam(name = "id")String id){
            System.out.println(id);
        Map<String, Object> map = new HashMap<>();
        try {
            // ComBine combine = cVrepository.findby(id).orElse(null);
            List<ComBine> combine  = cVrepository.appdetails(id);
            // System.out.println(total);
            map.put("status", 200);
            map.put("results", combine);
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }
    // 클럽멤버리스트 조회
    // 127.0.0.1:9090/ROOT/combineview/cmemberlist.json?no=10
    @GetMapping(value = "/cmemberlist.json",
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> cMemberListGet(
        @RequestParam(name = "no")long no){
            System.out.println(no);
        Map<String, Object> map = new HashMap<>();
        try {
            // ComBine combine = cVrepository.findby(id).orElse(null);
            List<ComBine> combine  = cVrepository.cmemberlList(no);
            // System.out.println(total);
            map.put("status", 200);
            map.put("results", combine);
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }
    // 권한변경 조회
    // 127.0.0.1:9090/ROOT/combineview/changeauth.json?no=10
    @GetMapping(value = "/changeauth.json",
    consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> changeauthGet(
        @RequestParam(name = "no")long no,
        @RequestParam(name = "id")String id
        ){
            System.out.println(no);
        Map<String, Object> map = new HashMap<>();
        try {
            List<ComBine> combine  = cVrepository.chageauth(no,id);
            // System.out.println(total);
            map.put("status", 200);
            map.put("results", combine);
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }
    
    
}
