$(() => {

    let stompClient = null;

    function updateView(isConnected) {
        $('#username').prop('disabled', isConnected);
        $('#connectBtn').prop('disabled', isConnected);
        $('#disconnectBtn').prop('disabled', !isConnected);
        $('#sendBtn').prop('disabled', !isConnected);
        $('#message').prop('disabled', !isConnected);
        $('#recipient').prop('disabled', !isConnected);
        if (isConnected) {
            $('#messages').text('');
        }
    }

    function onMessage(messageEvent) {
        console.log(messageEvent);
        const messageBody = JSON.parse(messageEvent.body);
        $(`<p>${messageBody.timestamp} ${messageBody.sender}: ${messageBody.text}</p>`).appendTo($('#messages'));
    }

    function onConnect() {
        updateView(true);
        stompClient.subscribe("/main-room", onMessage);
    }

    function getUser() {
        return $('#username').val();
    }

    function connect() {
        const user = getUser();
        const socket = new SockJS("/chat");
        stompClient = Stomp.over(socket);
        stompClient.connect({user}, onConnect);
    }

    function disconnect() {
        stompClient.disconnect();
        updateView(false);
    }

    function send() {
        const message = {
            sender: getUser(),
            recipients: $('#recipient').val().split(','),
            text: $('#message').val()
        };
        stompClient.send('/ws/chat', {}, JSON.stringify(message));
    }

    updateView(false);
    $('#connectBtn').click(connect);
    $('#disconnectBtn').click(disconnect);
    $('#sendBtn').click(send);

});
