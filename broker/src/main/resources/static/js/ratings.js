$(() => {

    const socket = new WebSocket('ws://localhost:9000/ratings');
    const rating = $('#rating');

    socket.onopen = () => {
        console.log("Connected");
    }

    socket.onclose = () => {
        console.log("Disconnected");
    }

    socket.onerror = (error) => {
        console.log(error);
    }

    socket.onmessage = (message) => {
        rating.text(message.data);
    }

    function onSend() {
        socket.send($('#message').val());
    }

    $('#sendBtn').click(onSend);

});
