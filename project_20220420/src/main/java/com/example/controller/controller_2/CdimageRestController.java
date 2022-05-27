package com.example.controller.controller_2;

import com.example.repository.repository_gibum.CimageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cdimage")
public class CdimageRestController {

    @Autowired
    CimageRepository CiRepository;

    @Autowired
	ResourceLoader resLoader;
    
}
