// package com.example;

// import java.nio.file.attribute.UserPrincipal;
// import java.security.Principal;
// import java.util.Map;
// import java.util.UUID;

// import org.springframework.http.server.ServerHttpRequest;
// import org.springframework.web.socket.WebSocketHandler;
// import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

// public class UserHandshakeHandler extends DefaultHandshakeHandler {

//     @Override
//     protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
//             Map<String, Object> attributes) {
        
//         final String randomId = UUID.randomUUID().toString();
//         LOG.info("User with ID '{}' opened the page", randomId);
       
//         return new UserPrincipal();       
//         // return super.determineUser(request, wsHandler, attributes);
//     }
    
// }
