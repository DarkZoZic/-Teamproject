package com.example.controller.controller_4;

import com.example.jwt.JwtUtil;
import com.example.repository.repository_4.QnaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/qna")
public class QnaRestController {

    @Autowired
    QnaRepository qRepository;
    
    @Autowired 
    JwtUtil jwtUtil;
    
}
