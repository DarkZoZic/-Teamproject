package com.example.controller.controller_4;

import com.example.entity.entity1.Greeting;
import com.example.entity.entity1.HelloMessage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

// @Controller
@RestController
public class GreetingController {
    
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting (HelloMessage message) throws Exception{
        Thread.sleep(500); // simulated delay 0.5초
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    
	@RequestMapping("/mychatt")
	public ModelAndView chatt() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
   
   
}
