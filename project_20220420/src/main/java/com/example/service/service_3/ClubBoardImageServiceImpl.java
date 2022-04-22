package com.example.service.service_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.entity2.CbImage;
import com.example.repository.repository_3.ClubBoardImageRepository;

@Service
public class ClubBoardImageServiceImpl implements ClubBoardImageService{
	@Autowired
	ClubBoardImageRepository cbiRep;
	
	@Override
	public int insertClubBoardImage(CbImage cbImage) {
		try {
			cbiRep.save(cbImage);
			return 1;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

}
