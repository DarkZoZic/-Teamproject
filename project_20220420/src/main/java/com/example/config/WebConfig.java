// package com.example.config;

// import com.example.UserHandshakeHandler;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
// import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
// import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
// import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// @Configuration
// @EnableWebSocketMessageBroker
// public class WebConfig implements WebSocketMessageBrokerConfigurer{

    



//     @Override
//     public void configureMessageBroker(MessageBrokerRegistry registry) {
//         // WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
//         registry.enableSimpleBroker("/topic");
//         registry.setApplicationDestinationPrefixes("/ws");
//     }

//     @Override
//     public void registerStompEndpoints(StompEndpointRegistry registry) {
//         // WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
//         registry.addEndpoint("/our-websocket")
//                 .setHandshakeHandler(new UserHandshakeHandler())
//                 .withSockJS();
//     }

    

    
// }
