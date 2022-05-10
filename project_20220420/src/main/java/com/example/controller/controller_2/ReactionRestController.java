package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.Reaction;
import com.example.entity.entity2.Board1;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_gibum.ReactionRepository;
import com.example.service.service_2.ReactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reaction")
public class ReactionRestController {

    @Autowired ReactionRepository rRepository;

    @Autowired JwtUtil jwtUtil;

    @Autowired ReactionService rService;


    // 좋아요기능
	// 127.0.0.1:9090/ROOT/reaction/like.json
	@RequestMapping(value = "/like.json", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> Likepost(
    @ModelAttribute Reaction reaction,
    @RequestHeader (name = "token")String token){
    System.out.println(reaction);
    Map<String, Object> map = new HashMap<>();
try {
    if(token != null){
        String username = jwtUtil.extractUsername(token);
        Member member = new Member();
        member.setMid((String) username );
        reaction.setMember(member);
        System.out.println(reaction.getMember().getMid());
    
        Board1 board1 = new Board1();
        board1.setBno((Long) reaction.getBoard().getBno());
        reaction.setBoard(board1);
        System.out.println(reaction.getBoard().getBno());

        // if(reaction.getRcount() == 0L){

        //     reaction.setRcount(1L);
        // }
        rRepository.save(reaction);

        // rService.updateReactionRcountOne(reaction.getBoard().getBno());
        
    }
    map.put("status", 200); // 성공
   
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
    // 좋아요 취소 기능
	// 127.0.0.1:9090/ROOT/reaction/unlike.json
	@RequestMapping(value = "/unlike.json", 
    method = { RequestMethod.PUT },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> UnlikePost(
    @ModelAttribute Reaction reaction,
    @RequestHeader (name = "token")String token){
        Map<String, Object> map = new HashMap<>();
    try {
        long user = reaction.getBoard().getBno();
    // System.out.println(reaction.getBoard().getBno()); == 1
    if(token != null){
        String username = jwtUtil.extractUsername(token);
        Member member = new Member();
        member.setMid((String) username );
        reaction.setMember(member);
    
        Board1 board1 = new Board1();
        board1.setBno((Long) reaction.getBoard().getBno());
        reaction.setBoard(board1);

    //     System.out.println(reaction.getBoard().getBno());
        if(username.equals(reaction.getMember().getMid())){
            if(reaction.getBoard().getBno() == user){
                Reaction read = rRepository.findByMember_MidAndBoard_Bno(username, user);
                read.setRcount(0L);
                rRepository.save(read);
            }
        }
        System.out.println("성공");
        
    //     // // reaction = rRepository.findrReaction(username, reaction.getBoard().getBno());
    //     // reaction.setRcount(0L);
    //     // // rService.updateReactionRcountOne(reaction.getBoard().getBno());
        
        
    }
    map.put("status", 200); // 성공
   
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
    
}
