// var stompClient = null;

// $(document).ready(function() {
//     console.log("index page is ready");
//     connect(); 

//     $("#send").click(function(){
//         sendMessage();

//     });

//     $("#send").click(function(){
//         sendMessage();

//     });
// })

// function connect() {
//     var socket = new SockJs("/our-websocket");
//     stompClient = Stomp.over(socket);
//     stompClient.connect({}, function (frame) {
//         console.log("Connected :" + frame);
//         stompClient.subscribe('/topic/messages', function (message) {
//             showMessage(JSON.parse(message.body).content);
//         });

//         stompClient.subscribe('/user/topic/private-message', function (message) {
//             showMessage(JSON.parse(message.body).content);
//         });
//     });
// }

// function showMessage(message) {
//     $('#messages').append("<tr><td>" + message + "<td><tr>");
// }

// function sendMessage() {
//     console.log("sending private message");
//     stompClient.send("/ws/private-message", {}, JSON.stringify({'messageContent : $("#message").val()}));
// }

// function sendMessage() {
//     console.log("sending message");
//     stompClient.send("/ws/message", {}, JSON.stringify({'messageContent : $("#message").val()}));
// }