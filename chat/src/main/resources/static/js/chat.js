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

    }

    function onConnect() {

    }

    function getUser() {

    }

    function connect() {

    }

    function disconnect() {

    }

    function send() {

    }

    updateView(false);
    $('#connectBtn').click(connect);
    $('#disconnectBtn').click(disconnect);
    $('#sendBtn').click(send);

});
