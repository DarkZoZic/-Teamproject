package com.example.controller.controller_4;

import java.security.Principal;

import com.example.dto.Message;
import com.example.dto.ResponseMessage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequestMapping(value = "/web")
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public ResponseMessage getMessage(final Message message) throws InterruptedException{

        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
        
    }

    @MessageMapping("/private-message")
    @SendTo("/topic/private-message")
    public ResponseMessage getPrivateMessage(final Message message,
                                            final Principal principal) throws InterruptedException{

        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape(
            "Sending private message to user" + principal.getName() + ": " + message.getMessageContent()));
        
    }

    @GetMapping(value = "/")
    public String insertGET(){
        return "/4/index";
    }
    
}
