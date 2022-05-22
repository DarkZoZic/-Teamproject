<script>
function connect(){
    var ws = new WebSocket("ws://localhost:8080/replyEcho?bno=1234");

    ws.onopen = function () {
        console.log('Info : connection opened.');
    };

    ws.onmessage = function (event) {
        console.log(event.data+'\n');
    };

    ws.onclose = function (event) { 
        console.log('Info : connection closed' ); 
        // setTimeout( function(){ connect(); }, 1000); // retry connection!
    };

    ws.onerror = function (err) { console.log('Error : ', err ); };
}

$('#btnSend').on('click', function(evt) {
    evt.preventDefault();
if (socket.readyState !== 1) return;
    let msg = $('input#msg').val();
    ws.send(msg);
});

</script>