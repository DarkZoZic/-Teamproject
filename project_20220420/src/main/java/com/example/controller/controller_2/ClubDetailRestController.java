package com.example.controller.controller_2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity1.CDImage;
import com.example.entity.entity1.ClubDetail;
import com.example.entity.entity2.Cimage;
import com.example.entity.entity2.Club;
import com.example.repository.repository_4.ClubDetailRepository;
import com.example.repository.repository_gibum.CdimageRepository;
import com.example.repository.repository_gibum.CimageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/clubdetail")
public class ClubDetailRestController {

    @Autowired
    CdimageRepository cdimageRepository;

    @Autowired ClubDetailRepository cdRepository;

    @Autowired CimageRepository cimageRepository;


    
    // 클럽디테일 리스트 받아오기
	// 127.0.0.1:9090/ROOT/clubdetail/selectlist
	@RequestMapping(value = "/selectlist", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
public Map<String, Object> selectselectlistGet(){
    Map<String, Object> map = new HashMap<>();
    try {

        List<ClubDetail> clubDetail = cdRepository.findAll();
        System.out.println(clubDetail);
        
        map.put("status", 200); 
        map.put("result", clubDetail); 

    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }

return map;
}
    // 클럽번호로 클럽디테일 가져오기
	// 127.0.0.1:9090/ROOT/clubdetail/selectcno
	@RequestMapping(value = "/selectcno", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
public Map<String, Object> selectcnoGet(
@RequestParam(value = "cno")long cno){
    Map<String, Object> map = new HashMap<>();
    try {

        ClubDetail clubDetail = cdRepository.findByClub_Cno(cno);
        System.out.println(clubDetail);
        Cimage cimage = cimageRepository.findByClub_Cno(cno);

            if(cimage != null){

                map.put("imgurl","/ROOT/club/cimage?cno=" + cno);
            }
            else{
                map.put("imgurl",null);

            }

        map.put("status", 200); 
        map.put("result", clubDetail); 

    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }

return map;
}
   
    // 클럽공고 이미지 등록
	// 127.0.0.1:9090/ROOT/clubdetail/cdimage
	@RequestMapping(value="/cdimage", 
    method={RequestMethod.POST}, 
    consumes = {MediaType.ALL_VALUE},
    produces= {MediaType.APPLICATION_JSON_VALUE})
public Map<String, Object> cdimagePOST(
    @ModelAttribute CDImage cdimage,
    @ModelAttribute MultipartFile[] file) throws IOException
{
Map<String, Object> map = new HashMap<>();
try 
{
    
        System.out.println(file.length);
        // cdimage.setClubDetail(cdimage.getClubDetail()); 
        if(file != null) 
        {
            if(file.length > 0)// 이미지파일 첨부시
            {
                for(int i=0; i<file.length; i++)
                {
                    CDImage cdImage2 = new CDImage();
                    cdImage2.setClubDetail(cdimage.getClubDetail());
                    System.out.println("file[i] : " + file[i].getContentType());
                    cdImage2.setCdimage(file[i].getBytes());
                    cdImage2.setCdimagename(file[i].getOriginalFilename());
                    cdImage2.setCdimagesize(file[i].getSize());
                    cdImage2.setCdimagetype(file[i].getContentType());
                    cdimageRepository.save(cdImage2);
                    map.put("status", 200);
                }	
            }
            
        }
        //  ClubDetail clubDetail = cdRepository.findByClub_Cno(cdimage.getClubDetail().getCdno());
        //  if(clubDetail == null){
        //     map.put("status", 0);

        //  }

   
        } 
        catch (Exception e){   
            e.printStackTrace();
            map.put("status", -1);
        }
        return map;
    }



    // 클럽 이미지n개조회(클럽디테일에서 표시됨)
	// 127.0.0.1:9090/ROOT/club/detailselectone?cno=189
	@RequestMapping(value = "/detailselectone", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> detailselectoneGet(
    @RequestParam(value = "cno")long cno){
    Map<String, Object> map = new HashMap<>();
    try {
        ClubDetail clubDetail = cdRepository.findByClub_Cno(cno);
        System.out.println(clubDetail);
        
        map.put("status", 200); 
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }

    return map;
    }
    // 클럽 이미지1개조회(클럽생성시 바로 받아오기)
	// 127.0.0.1:9090/ROOT/club/selectone?cno=175
	@RequestMapping(value = "/selectone", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> stomerMypageGet(
    @RequestParam(value = "cno")long cno){
        Map<String, Object> map = new HashMap<>();
    try {
        
        map.put("status", 200); 
    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }

return map;
}
    
}
