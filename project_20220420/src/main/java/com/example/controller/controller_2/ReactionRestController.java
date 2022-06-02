package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity1.ClubGallery;
import com.example.entity.entity1.Member;
import com.example.entity.entity1.Reaction;
import com.example.entity.entity2.Board1;
import com.example.entity.entity2.CReply;
import com.example.entity.entity2.ClubBoard;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.Board1Repository;
import com.example.repository.repository_gibum.ReactionRepository;
import com.example.service.service_2.ReactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reaction")
public class ReactionRestController {

    @Autowired ReactionRepository rRepository;

    @Autowired JwtUtil jwtUtil;

    @Autowired ReactionService rService;

    @Autowired 
    Board1Repository b1Repository;


    // 클럽 갤러리 좋아요기능
	// 127.0.0.1:9090/ROOT/reaction/gallerylike.json
	@RequestMapping(value = "/gallerylike.json", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> GalleryLikepost(
    @ModelAttribute Reaction reaction,
    @RequestHeader (name = "token")String token){
    Map<String, Object> map = new HashMap<>();
    long cbno = reaction.getClubgallery().getCgno();
try {
    if(token != null){
        String username = jwtUtil.extractUsername(token);
        Member member = new Member();
        member.setMid((String) username );
        reaction.setMember(member);
        System.out.println(reaction.getMember().getMid());
        
        ClubGallery clubGallery = new ClubGallery();
        clubGallery.setCgno((Long) reaction.getClubgallery().getCgno());
        reaction.setClubgallery(clubGallery);
        System.out.println(reaction.getClubgallery().getCgno());
        Reaction reaction2 = rRepository.findByMember_MidAndClubgallery_Cgno(username, cbno);
        if( reaction2 == null ){
            reaction.setRcount(1L);
            rRepository.save(reaction);
            map.put("status", 200); // 성공
        }
        else{
            
            map.put("status", -1); // 실패
            System.out.println("이미좋아요 누름");
        }
        
    }
   
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}


    // 클럽게시판 좋아요 취소 기능
	// 127.0.0.1:9090/ROOT/reaction/unlikegallery.json
	@RequestMapping(value = "/unlikegallery.json", 
    method = { RequestMethod.DELETE },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> UnlikeGalleryPost(
    @ModelAttribute Reaction reaction,
    @RequestHeader (name = "token")String token){
        Map<String, Object> map = new HashMap<>();
    try {
        long user = reaction.getClubgallery().getCgno();
    if(token != null){
        String username = jwtUtil.extractUsername(token);
                Reaction read = rRepository.findByMember_MidAndClubgallery_Cgno(username, user);
                rRepository.delete(read);
        
        
        
    }
    map.put("status", 200); 
   
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
    // 클럽게시판 좋아요 취소 기능
	// 127.0.0.1:9090/ROOT/reaction/unlikeclub.json
	@RequestMapping(value = "/unlikeclub.json", 
    method = { RequestMethod.DELETE },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> UnlikeClubPost(
    @ModelAttribute Reaction reaction,
    @RequestHeader (name = "token")String token){
        Map<String, Object> map = new HashMap<>();
    try {
        long user = reaction.getClubboard().getCbno();
    if(token != null){
        String username = jwtUtil.extractUsername(token);
                Reaction read = rRepository.findByMember_MidAndClubboard_Cbno(username, user);
                rRepository.delete(read);
        
        
        
    }
    map.put("status", 200); 
   
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}


    // 클럽 게시판 좋아요기능
	// 127.0.0.1:9090/ROOT/reaction/clublike.json
	@RequestMapping(value = "/clublike.json", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> ClubLikepost(
    @ModelAttribute Reaction reaction,
    @RequestHeader (name = "token")String token){
    Map<String, Object> map = new HashMap<>();
    long cbno = reaction.getClubboard().getCbno();
try {
    if(token != null){
        String username = jwtUtil.extractUsername(token);
        Member member = new Member();
        member.setMid((String) username );
        reaction.setMember(member);
        System.out.println(reaction.getMember().getMid());
        
        ClubBoard clubboard = new ClubBoard();
        clubboard.setCbno((Long) reaction.getClubboard().getCbno());
        reaction.setClubboard(clubboard);
        System.out.println(reaction.getClubboard().getCbno());
        Reaction reaction2 = rRepository.findByMember_MidAndClubboard_Cbno(username, cbno);
        if( reaction2 == null ){
            reaction.setRcount(1L);
            rRepository.save(reaction);
            map.put("status", 200); // 성공
        }
        else{
            
            map.put("status", -1); // 실패
            System.out.println("이미좋아요 누름");
        }
        
    }
   
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
    // 좋아요기능
	// 127.0.0.1:9090/ROOT/reaction/like.json
	@RequestMapping(value = "/like.json", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> Likepost(
    @ModelAttribute Reaction reaction,
    @RequestHeader (name = "token")String token){
    Map<String, Object> map = new HashMap<>();
    long bno = reaction.getBoard1().getBno();
try {
    if(token != null){
        String username = jwtUtil.extractUsername(token);
        Member member = new Member();
        member.setMid((String) username );
        reaction.setMember(member);
        // System.out.println(reaction.getMember().getMid());
        
        Board1 board1 = new Board1();
        board1.setBno((Long) reaction.getBoard1().getBno());
        // reaction.getBoard1(board1);
        // System.out.println(reaction.getBoard().getBno());
        Reaction reaction2 = rRepository.findByMember_MidAndBoard1_Bno(username, bno);
        if( reaction2 == null ){
            reaction.setRcount(1L);
            rRepository.save(reaction);
            map.put("status", 200); // 성공
        }
        else{
            
            map.put("status", -1); // 실패
            System.out.println("이미좋아요 누름");
        }
        
    }
   
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
    // -- 좋아요 가져오기(자유게시판디테일) --
    //127.0.0.1:9090/ROOT/reaction/likeone
    @RequestMapping(value = "/likeone",
            method = { RequestMethod.GET },
            consumes = { MediaType.ALL_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> likeoneGET(
        @RequestParam (name = "bno")long bno, 
        @RequestHeader (name = "token") String token ){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Member memberEntity = new Member();
            memberEntity.setMid(userid);
            System.out.println(memberEntity);
            Reaction reaction = new Reaction();

            reaction.setMember(memberEntity);
            System.out.println(reaction.toString());

            if(token !=null) {
                Reaction reaction1 = rRepository.findByBoard1_BnoAndMember_Mid(bno, userid);
                if(reaction1 == null){
                    map.put("status",0);
                        
                    }
                    else{
    
                        map.put("result",reaction1);
                        map.put("status",1);
                    }
                

                
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", -1); // 실패
        }
        return map;
    }

    // 자유게시판 좋아요 삭제
    @RequestMapping(value = "/unlike.json", method = {RequestMethod.DELETE}, consumes = {MediaType.ALL_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> boardDeletePost(@RequestParam(name = "bno") Long bno,
                            @RequestHeader (name = "token")String token){
    // 키를 알고 보내야 함. 틀리면 안감. er다이어그램 보면 됨

    Map<String ,Object> map = new HashMap<>();

    try{
        // 토큰 추출
        String userid = jwtUtil.extractUsername(token);
        System.out.println("USERNAME ==>" + userid);

        Reaction result= rRepository.findByMember_midAndBoard1_bno(userid, bno);
        rRepository.deleteById(result.getRcode());
        map.put("status", 200);

    }
    catch(Exception e){
        e.printStackTrace();
        map.put("status", 0); // 실패
    }
    return map;
    }

    // 댓글 좋아요 개수 조회
	// 127.0.0.1:9090/ROOT/reaction/relikelist.json
	@RequestMapping(value = "/relikelist.json", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> relikelistPost(
    @Param(value = "renumber")long renumber){
        Map<String, Object> map = new HashMap<>();
    try {
        System.out.println(renumber);
        List<Reaction> reaction = rRepository.findByCreply_Renumber(renumber);
        System.out.println(reaction.size());

        
        map.put("status", 200); 
        map.put("result", reaction.size()); 
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
        return map;
    }


    // 자유게시판 좋아요 개수 조회
	// 127.0.0.1:9090/ROOT/reaction/likelist.json
	@RequestMapping(value = "/likelist.json", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> likelistPost(
    @Param(value = "bno")long bno){
        Map<String, Object> map = new HashMap<>();
    try {
        // Board1 board = new Board1();
        // bno = board.getBno();
        System.out.println(bno);
        List<Reaction> reaction = rRepository.findByReaction_B_no(bno);
        System.out.println(reaction.size());

        
        map.put("status", 200); 
        map.put("result", reaction.size()); 
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
        return map;
    }

    // 클럽게시판 좋아요 개수 조회
	// 127.0.0.1:9090/ROOT/reaction/clublikelist.json
	@RequestMapping(value = "/clublikelist.json", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> clublikelistPost(
    @RequestBody ClubBoard clubBoard){
        Map<String, Object> map = new HashMap<>();
    try {
        long cbno = clubBoard.getCbno();
        System.out.println(cbno);
        List<Reaction> reaction = rRepository.findByReaction_Cb_no(cbno);
        System.out.println(reaction.size());

        
        map.put("status", 200); 
        map.put("result", reaction.size()); 
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
    // 클럽갤러리 좋아요 개수 조회
	// 127.0.0.1:9090/ROOT/reaction/gallerylikelist.json
	@RequestMapping(value = "/gallerylikelist.json", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> gallerylikelistPost(
    @RequestBody ClubGallery clubGallery){
        Map<String, Object> map = new HashMap<>();
    try {
        long cgno = clubGallery.getCgno();
        System.out.println(cgno);
        List<Reaction> reaction = rRepository.findByReaction_Cg_no(cgno);
        System.out.println(reaction.size());

        
        map.put("status", 200); 
        map.put("result", reaction.size()); 
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}


    // 댓글 좋아요기능
	// 127.0.0.1:9090/ROOT/reaction/relike.json
	@RequestMapping(value = "/relike.json", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> reLikepost(
    @ModelAttribute Reaction reaction,
    @RequestHeader (name = "token")String token){
    Map<String, Object> map = new HashMap<>();
    try {
        if(token != null){
            System.out.println("----------"+reaction.toString());

            String username = jwtUtil.extractUsername(token);
            Member member = new Member();
            member.setMid((String) username );
            reaction.setMember(member);
            // System.out.println(reaction.getMember().getMid());
            
            CReply cReply = new CReply();
            cReply.setRenumber(reaction.getCreply().getRenumber());
            reaction.setCreply(cReply);
            System.out.println("댓글번호======="+reaction.getCreply().getRenumber());

            // Board1 board1 = new Board1();
            // board1.setBno((Long) reaction.getBoard().getBno());
            // reaction.setBoard(board1);
            // System.out.println(reaction.getBoard().getBno());

            // Reaction reaction2 = rRepository.findByMember_MidAndBoard_Bno(username, bno);
            Reaction reaction2 = rRepository.findByMember_midAndCreply_renumber(username, reaction.getCreply().getRenumber());
            System.out.println(reaction2.toString());
            // if( reaction2 == null ){
            //     reaction.setRcount(1L);
            //     rRepository.save(reaction);
            //     map.put("status", 200); // 성공
            // }
            // else{
                
            //     map.put("status", -1); // 실패
            //     System.out.println("이미좋아요 누름");
            // }
            map.put("status", 200);
            
        }
    
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        
        return map;
    }


    // -- 좋아요 가져오기( 댓글 디테일 ) --
    //127.0.0.1:9090/ROOT/reaction/relikeone
    @RequestMapping(value = "/relikeone",
            method = { RequestMethod.GET },
            consumes = { MediaType.ALL_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> relikeoneGET(
        @RequestParam (name = "renumber")long renumber, 
        @RequestHeader (name = "token") String token ){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            // 토큰 추출
            String userid = jwtUtil.extractUsername(token);
            System.out.println("USERNAME ==>" + userid);

            Member memberEntity = new Member();
            memberEntity.setMid(userid);
            System.out.println(memberEntity);
            Reaction reaction = new Reaction();

            reaction.setMember(memberEntity);
            System.out.println(reaction.toString());

            if(token !=null) {
                Reaction reaction1 = rRepository.findByCreply_RenumberAndMember_Mid(renumber, userid);
                if(reaction1 == null){
                    map.put("status",0);
                }
                else{
                    map.put("result",reaction1);
                    map.put("status",1);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", -1); // 실패
        }
        return map;
    }

    
}
