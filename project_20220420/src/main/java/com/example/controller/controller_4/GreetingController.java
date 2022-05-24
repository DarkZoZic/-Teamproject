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

    
    // 서버 돌리고 http://localhost:9090/ROOT/mychatt
    // 원래 홈페이지에 보면 GreetingController는 그냥 controller에 19-24라인임. 이건 블로그에서 가져온 부분
    // https://jobtc.tistory.com/59
	@RequestMapping("/mychatt")
	public ModelAndView chatt() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("4/index");
		return mv;
	}
   
   
}
