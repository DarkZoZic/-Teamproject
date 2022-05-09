package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.entity2.Club;
import com.example.entity.entity2.Combineaddr;
import com.example.repository.repository_gibum.AddressRepository;
import com.example.repository.repository_gibum.ClubRepository;
import com.example.repository.repository_gibum.CombineaddrViewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/search")
public class SearchRestController {

	@Autowired CombineaddrViewRepository cViewRepository;
    

    // 통합검색
	// 127.0.0.1:9090/ROOT/search/search1
	@RequestMapping(value = "/search1", 
			method = { RequestMethod.GET },
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> searchidget(
		@RequestParam(name = "title")String title){
			System.out.println(title);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0); // 정상적이지 않을때
			try {
				List<Combineaddr> club = cViewRepository.Search(title);
				List<Combineaddr> club2 = cViewRepository.Search2(title);
				// List<Combineaddr> 통합 =
				System.out.println(club2);
                
				map.put("status", 200); 
				map.put("results", club); 

			}	
			 catch (Exception e) {
				e.printStackTrace();
			}

		return map;
	}

    
}
