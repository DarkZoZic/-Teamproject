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
	public int insertClubBoardImage(CbImage cbImage) 
	{
		try 
		{
			cbiRep.save(cbImage);
			return 1;
		} 
		catch (Exception e) 
		{
			return 0;
		}
		
	}

	@Override
	public CbImage selectClubBoardImage(long cbiImgcode) 
	{
		try 
		{
			CbImage cbImage = cbiRep.findByCbiImgcode(cbiImgcode);
			return cbImage;
		} 
		catch (Exception e) 
		{
			return null;
		}
		
	}

	@Override
	public int updateClubBoardImage(CbImage cbImage) {
		try 
		{
			cbImage.setCbiImage(cbImage.getCbiImage());
			cbImage.setCbiImagename(cbImage.getCbiImagename());
			cbImage.setCbiImagesize(cbImage.getCbiImagesize());
			cbImage.setCbiImagetype(cbImage.getCbiImagetype());
			cbiRep.deleteById(cbImage.getCbiImgcode());
			cbiRep.save(cbImage);
			return 1;
		} catch (Exception e) 
		{
			return 0;
		}
		
	}

}
