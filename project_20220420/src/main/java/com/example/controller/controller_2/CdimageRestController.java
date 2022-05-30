package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity1.CDImage;
import com.example.entity.entity1.ClubDetail;
import com.example.repository.repository_gibum.CdimageRepository;
import com.example.repository.repository_gibum.CimageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cdimage")
public class CdimageRestController {

    @Autowired
    CimageRepository CiRepository;

    @Autowired
    CdimageRepository cdimageRepository;

    @Autowired
	ResourceLoader resLoader;

    // 클럽디테일 이미지 가져오기
	// 127.0.0.1:9090/ROOT/cdimage/selectimage
	@RequestMapping(value = "/selectimage", 
    method = { RequestMethod.GET },
    consumes = { MediaType.ALL_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE })
public Map<String, Object> selectcno1Get(
@RequestParam(value = "cdno")long cdno){
    Map<String, Object> map = new HashMap<>();
    try {
        ClubDetail clubDetail = new ClubDetail();
        List<CDImage> cdimage = cdimageRepository.findByClubDetail_cdno(cdno);
        System.out.println(cdimage);

        
        map.put("status", 200); 
        map.put("result", cdimage); 

    }
    catch (Exception e) {
        e.printStackTrace();
        map.put("status", 0);
    }

return map;
}
    
}
