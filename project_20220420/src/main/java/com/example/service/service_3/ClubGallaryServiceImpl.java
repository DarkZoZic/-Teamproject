package com.example.service.service_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.repository_3.ClubGallaryRepository;

@Service
public class ClubGallaryServiceImpl {
	@Autowired
	ClubGallaryRepository cgRep;

}
