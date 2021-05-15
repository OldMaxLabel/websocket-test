package com.example.websockettest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    static int i = 0;

    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
    @Scheduled(fixedDelay = 5000L)
    public void greeting() {

        Greeting greeting = new Greeting("Hello: " + i++ + "!");

        messagingTemplate.convertAndSendToUser(
                "1",
                "/queue/greetings",
                greeting.getContent());

        System.out.println(greeting.getContent());

    }

}
