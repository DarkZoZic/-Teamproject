package com.example.controller.controller_4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity1.Like;
import com.example.entity.entity1.Member;
import com.example.entity.entity2.Club;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.LikeRepository;
import com.example.service.service_4.LikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/like")
public class LikeRestController {

    @Autowired
    LikeRepository lRepository;

    @Autowired
    LikeService lService;

    @Autowired 
    JwtUtil jwtUtil;

    // -- 찜 추가 -- 
    //127.0.0.1:9090/ROOT/like/insert
    @RequestMapping(value = "/insert", 
        method = {RequestMethod.POST},
        consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> likeInsertPost(
        @RequestBody  Like like,
        @RequestHeader (name = "token") String token ) {

        System.out.println("------" + like.toString());

        // 0406 BuyRestController.java

        Map<String, Object> map = new HashMap<>();
        try{
            // 토큰에서 아이디 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            // 회원 엔티티 객체 생성 및 아이디 추가
            Member memberEntity = new Member();
            memberEntity.setMId(userid);

            // 클럽 엔티티 
            Club clubEntity = new Club();
            clubEntity.setCNo(like.getClub().getCNo());

            // 찜 엔티티에 추가
            like.setMember(memberEntity);
            like.setClub(clubEntity);

            System.out.println("------" + like.toString());

            if(token != null){
                // 저장소를 이용해서 db에 추가
                lRepository.save(like);
                map.put("status", 200);
            }
           map.put("status", 200);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status",0);
        }
        return map;
    }

    //127.0.0.1:9090/ROOT/like/insert1
    @RequestMapping(value = "/insert1", 
        method = {RequestMethod.POST},
        consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> InsertPost1(
        @RequestBody Like like,
        @RequestParam(name="btn") String btn,
		@RequestParam(name = "code") long lNo,
		@RequestParam(name = "cnt") long cnt,
        @RequestHeader (name = "token") String token ) {

        Map<String ,Object> map = new HashMap<>();

        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Member memberEntity = new Member();
            memberEntity.setMId(userid);
            System.out.println(memberEntity);

            like.setMember(memberEntity);
            System.out.println(like.toString());

            if(token !=null) {
                lRepository.save(like);
                System.out.println(like.toString());
       
                map.put("status", 200); // 성공
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }

    // -- 찜 목록 --
    // 엔티티에 있는걸로 해야하나? 아니면 그냥 바로 mId로 해도 되나? 
    @RequestMapping(value = "/selectlist", method = { RequestMethod.GET },
                    consumes = { MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> buySelectListGET( 
        Like like,
        @RequestHeader (name = "token") String token ){

        Map<String, Object> map = new HashMap<String, Object>();
        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Member memberEntity = new Member();
            memberEntity.setMId(userid);
            System.out.println(memberEntity);

            like.setMember(memberEntity);
            System.out.println(like.toString());

            if(token !=null) {
                // List<Like> like1 = lRepository.findByMember_mIdOrderByLNoAsc(userid);
       
                // map.put("result",like1);
                map.put("status",200);
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", 0); // 실패
        }
        return map;
    }


    // -- 찜 1개 삭제 -- 
    //127.0.0.1:9090/ROOT/like/deleteone
    @RequestMapping(value = "/deleteone", 
        method = {RequestMethod.POST},
        consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> deleteOnePOST(
        @RequestBody  Like like,
        @RequestHeader (name = "token") String token ) {
            // System.out.println(token);
            // System.out.println(like.getLNo());

        Map<String, Object> map = new HashMap<>();
        try{

            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            // System.out.println("USERNAME ==>" + userid);

            Like like1 = lRepository.getById(like.getLNo());

            // System.out.println("찜번호" + like.getLNo());
            // System.out.println("찜회원 아이디" + like1.getMember().getMId());

           if( userid.equals( like1.getMember().getMId() )){
                lRepository.deleteById(like.getLNo());
                map.put("status", 200); // 성공
            }
            else if( !userid.equals( like1.getMember().getMId() )){
                map.put("status", 0);
            } 
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status",-1);
        }
        return map;
    }

    // -- 찜 일괄 삭제 --
    //127.0.0.1:9090/ROOT/like/deletebatch
        @RequestMapping(value = "/deletebatch", 
        method = {RequestMethod.GET},
        consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> deleteBatchPOST(
        // @RequestBody List<Like> like,
        // @RequestBody List <Map<String, Object>> like,
        @RequestParam(name = "lNo") Long[] lNo,
        @RequestHeader (name = "token") String token ) {
            // System.out.println(token);
            // System.out.println(like.getLNo());

        Map<String, Object> map = new HashMap<>();
        try{
            System.out.println(lNo);
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            // Like like1 = lRepository.getById(lNo);

            // System.out.println("찜번호" + like.getLNo());
            // System.out.println("찜회원 아이디" + like1.getMember().getMId());

            // if( userid.equals( like1.getMember().getMId() )){
            //     lService.deleteLikeBatch(lNo);
            //     map.put("status", 200); // 성공
            // }
            // else if( !userid.equals( like1.getMember().getMId() )){
            //     map.put("status", 0);
            // } 
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status",-1);
        }
        return map;
    }


    
}
