// package com.example.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
// import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
// import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// import lombok.RequiredArgsConstructor;

// @Configuration
// @EnableWebSocketMessageBroker
// @RequiredArgsConstructor
// public class WebsocketConfig implements WebSocketMessageBrokerConfigurer{

//     private final stompHandler stompHandler; // jwt 토큰 인증 핸들러

//     @Override
//     public void registerStompEndpoints(StompEndpointRegistry registry) {
//         registry.addEndpoint("/ws-stomp")
//                 .withSockJS();
//     }

//     @Override
//     public void configureMessageBroker(MessageBrokerRegistry registry) {
//         registry.enableSimpleBroker("/sub");
//         registry.setApplicationDestinationPrefixes("/pub");
//     }

//     @Override
//     public void configureClientInboundChannel(ChannelRegistration registration) {
//         registration.interceptors(stompHandler); // 핸들러 등록
//     }
// }
