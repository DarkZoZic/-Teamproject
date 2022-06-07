package com.example.controller.controller_2;

import java.util.ArrayList;
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
    // 127.0.0.1:9090/cluver/cdimage/selectimage
    @RequestMapping(value = "/selectimage", method = { RequestMethod.GET }, consumes = {
            MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> selectcno1Get(
            @RequestParam(value = "cdno") long cdno) {
        Map<String, Object> map = new HashMap<>();
        try {
            ClubDetail clubDetail = new ClubDetail();
            List<Map<String, Object>> list = new ArrayList<>();
            List<CDImage> cdimage = cdimageRepository.findByClubDetail_cdno(cdno);
            for (CDImage obj : cdimage) {
                Map<String, Object> map1 = new HashMap<>();
                map1.put("obj", obj);

                if (cdimage != null) {

                    map1.put("imgurl", "/cluver/clubdetail/cdimage?cdno=" +
                            obj.getClubDetail().getCdno() + "&idx=0");
                } else {
                    map1.put("imgurl", null);

                }
                list.add(map1);
            }

            map.put("status", 200);
            map.put("result", list);
            map.put("개수", cdimage.size());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }

        return map;
    }
}
