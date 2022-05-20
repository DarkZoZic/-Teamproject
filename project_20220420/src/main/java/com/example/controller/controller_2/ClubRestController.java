package com.example.controller.controller_2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity2.Address;
import com.example.entity.entity2.Category;
import com.example.entity.entity2.Club;
import com.example.jwt.JwtUtil;
import com.example.repository.repository_gibum.ClubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/club")
public class ClubRestController {
    @Autowired ClubRepository cRepository;

    @Autowired JwtUtil jwtUtil;


    // 127.0.0.1:9090/ROOT/club/image?cno=179
	// @GetMapping(value ="/image")
    // public ResponseEntity<byte[]> imageGET(
    //     @RequestParam(name ="cno") Long cno) throws IOException {
    //         // 이미지명, 이미지크기, 이미지종류, 이미지데이터
	// 		Club club = cRepository.findByCno(cno);

    //         if(club != null){ // 물품정보가 존해하면
    //             if(club.getCimagesize() > 0) { // 첨부한 파일 존재
    //                 HttpHeaders headers = new HttpHeaders();
                    
    //                 if(club.getCimagetype().equals("image/jpeg")){
    //                     headers.setContentType(MediaType.IMAGE_JPEG);
    //                 }

    //                 else if(club.getCimagetype().equals("image/png")){
    //                     headers.setContentType(MediaType.IMAGE_PNG);
    //                 }

    //                 else if(club.getCimagetype().equals("image/gif")){
    //                     headers.setContentType(MediaType.IMAGE_GIF);
    //                 }

    //                 // 이미지 byte[], headers, HttpStatus.OK
    //                 ResponseEntity<byte[]> response 
    //                 = new ResponseEntity<>(club.getCthumbnail(),
    //                     headers, HttpStatus.OK);
    //                     return response;
    //             }
    //             // else {
    //             //     InputStream is =
    //             //     resLoader
    //             //     .getResource("classpath:/static/img/default.png")
    //             //     .getInputStream();

    //             //     HttpHeaders headers = new HttpHeaders();
    //             //     headers.setContentType(MediaType.IMAGE_PNG);

    //             //     ResponseEntity<byte[]> response
    //             //     = new ResponseEntity<>(is.readAllBytes(),
    //             //     headers, HttpStatus.OK);

    //             //     return response;
    //             // }
    //         }
    //         return null;
    //     }

    // 클럽생성
	// 127.0.0.1:9090/ROOT/club/insert.json
	//{"mid":"c1", "mpw":"c1" };
	@RequestMapping(value = "/insert.json", 
    method = { RequestMethod.POST },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
public Map<String, Object> JoinClubpost(
    @ModelAttribute Club club,
    @RequestParam(name = "file",required = false) MultipartFile file,
    @RequestHeader(name = "TOKEN") String token){
    System.out.println(club);
Map<String, Object> map = new HashMap<>();
try {
    String username = jwtUtil.extractUsername(token);
    System.out.println(username);
    System.out.println(file);
    // if(
//     file != null){
//     if(!file.isEmpty()){
//         club.setCthumbnail(file.getBytes());
//         club.setCimagesize(file.getSize());
//         club.setCimagetype(file.getContentType());
//         club.setCimagename(file.getOriginalFilename());
        
//     }
// }
    // Address address = new Address();
    // address.setAcode((Long) club.getAddress().getAcode());

    // Category category = new Category();
    // category.setCgcode((Long) club.getCategory().getCgcode());
    
    cRepository.save(club);
    map.put("status", 200);
    map.put("result", club);
    map.put("mid", username);
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }
    
    return map;
}
    // 클럽1개 조회(클럽생성시 바로 받아오기)
	// 127.0.0.1:9090/ROOT/club/selectone
	@RequestMapping(value = "/selectone", 
			method = { RequestMethod.GET },
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> customerMypageGet(
		@RequestParam(value = "cno")long cno){
            Map<String, Object> map = new HashMap<>();
            try {
                
                Club club = cRepository.findByCno(cno);
                System.out.println(club);
                map.put("status", 200); 
            }
            catch (Exception e) {
                e.printStackTrace();
                map.put("status", 0);
            }

		return map;
	}
    // 클럽리스트 조회()
	// 127.0.0.1:9090/ROOT/club/selectlist
	@RequestMapping(value = "/selectlist", 
			method = { RequestMethod.GET },
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> customerMypageGet(){
            Map<String, Object> map = new HashMap<>();
            try {
                String private12 = "공개";
                List<Club> club = cRepository.findByCprivate(private12);


                System.out.println(club);
		        // club.set ("/ROOT/member/image?mid=" +username);
                map.put("status", 200); 
                map.put("result", club); 
            }
            catch (Exception e) {
                e.printStackTrace();
                map.put("status", 0);
            }

		return map;
	}
    
}
