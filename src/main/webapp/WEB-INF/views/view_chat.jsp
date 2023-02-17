<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>웹소켓 채팅</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
	<!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
<!--   <link rel="stylesheet" type="text/css" href="./css/style.css"> -->
<!--     jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
    integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
    integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
    crossorigin="anonymous"></script>
    <style>
    *{
	box-sizing:border-box;
}
body{
	background-color:#abd9e9;
	font-family:Arial;
}
#container{
	width:100%;
	height:100%;
	background:#eff3f7;
	margin:0 auto;
	font-size:0;
	border-radius:5px;
	overflow:hidden;
}
aside{
	width:260px;
	height:800px;
	background-color:#3b3e49;
	display:inline-block;
	font-size:15px;
	vertical-align:top;
}
main{
	width:70%;
	height:800px;
	display:inline-block;
	font-size:15px;
	vertical-align:top;
}

aside header{
	padding:30px 20px;
}
aside input{
	width:100%;
	height:50px;
	line-height:50px;
	padding:0 50px 0 20px;
	background-color:#5e616a;
	border:none;
	border-radius:3px;
	color:#fff;
	background-image:url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/1940306/ico_search.png);
	background-repeat:no-repeat;
	background-position:170px;
	background-size:40px;
}
aside input::placeholder{
	color:#fff;
}
aside ul{
	padding-left:0;
	margin:0;
	list-style-type:none;
	overflow-y:scroll;
	height:690px;
	width: 80%;
}
aside li{
	padding:10px 0;
}
aside li:hover{
	background-color:#5e616a;
}
h2,h3{
	margin:0;
}
aside li img{
	border-radius:50%;
	margin-left:20px;
	margin-right:8px;
}
aside li div{
	display:inline-block;
	vertical-align:top;
	margin-top:12px;
}
aside li h2{
	font-size:14px;
	color:#fff;
	font-weight:normal;
	margin-bottom:5px;
}
aside li h3{
	font-size:12px;
	color:#7e818a;
	font-weight:normal;
}

.status{
	width:8px;
	height:8px;
	border-radius:50%;
	display:inline-block;
	margin-right:7px;
}
.green{
	background-color:#58b666;
}
.orange{
	background-color:#ff725d;
}
.blue{
	background-color:#6fbced;
	margin-right:0;
	margin-left:7px;
}

main header{
	height:110px;
	padding:30px 20px 30px 40px;
}
main header > *{
	display:inline-block;
	vertical-align:top;
}
main header img:first-child{
	border-radius:50%;
}
main header img:last-child{
	width:24px;
	margin-top:8px;
}
main header div{
	margin-left:10px;
	margin-right:145px;
}
main header h2{
	font-size:16px;
	margin-bottom:5px;
}
main header h3{
	font-size:14px;
	font-weight:normal;
	color:#7e818a;
}

#chat{
	padding-left:0;
	margin:0;
	list-style-type:none;
	overflow-y:scroll;
	height:535px;
	border-top:2px solid #fff;
	border-bottom:2px solid #fff;
}
#chat li{
	padding:10px 30px;
}
#chat h2,#chat h3{
	display:inline-block;
	font-size:13px;
	font-weight:normal;
}
#chat h3{
	color:#bbb;
}
#chat .entete{
	margin-bottom:5px;
}
#chat .message{
	padding:20px;
	color:#fff;
	line-height:25px;
	max-width:90%;
	display:inline-block;
	text-align:left;
	border-radius:5px;
}
#chat .me{
	text-align:right;
}
#chat .you .message{
	background-color:#58b666;
}
#chat .me .message{
	background-color:#6fbced;
}
#chat .triangle{
	width: 0;
	height: 0;
	border-style: solid;
	border-width: 0 10px 10px 10px;
}
#chat .you .triangle{
		border-color: transparent transparent #58b666 transparent;
		margin-left:15px;
}
#chat .me .triangle{
		border-color: transparent transparent #6fbced transparent;
		margin-left:375px;
}

main footer{
	height:155px;
	padding:20px 30px 10px 20px;
}
main footer textarea{
	resize:none;
	border:none;
	display:block;
	width:100%;
	height:80px;
	border-radius:3px;
	padding:20px;
	font-size:13px;
	margin-bottom:13px;
}
main footer textarea::placeholder{
	color:#ddd;
}
main footer img{
	height:30px;
	cursor:pointer;
}
main footer a{
	text-decoration:none;
	text-transform:uppercase;
	font-weight:bold;
	color:#6fbced;
	vertical-align:top;
	margin-left:333px;
	margin-top:5px;
	display:inline-block;
}
    </style>
   <!--  <link rel="stylesheet" type="text/css"href="/css/view_chat.css"> -->
	<script type="text/javascript">
		var webSocket = {
			init: function(param) {
				const spaceid = '${spaceid}';
				this._url = param.url;
				this._initSocket();
				//this._sendMessage($('#name').val()+'님이 접속하셨습니다.');
				this._sendMessage('입장/${userid}/' + spaceid +'님이 접속하셨습니다.');
			},
			sendChat: function() {//메세지 보내기 
				const username = '${username}';
				this._sendMessage(username +$('#message').val());
				$('#message').val('');
			},
			receiveMessage: function(str) {//메세지 받기

				
				
				$('#chat').append(
						'<li class="me">'
						+'<div class="entete">'
						+'<h3>10:12AM, Today</h3>'
						+'&nbsp;'
						+'<h2> Vincent</h2>'
						+'<span class="status blue"></span>'
						+'</div>'
						+'<div class = "message">'+ str + '</div>' 
						+ '</li>'
						);

			},
			
			/* load: function PreMessage(roomno){//불러오기?
				let requestdata = {"roomno" : roomno};
				let data = JSON.stringify(requestdata);
				$.ajax({
					type: "post",
					url: "preRoom.htm",
					data: data,
					dataType: "text",
					contentType: "application/json; charset=utf-8",
					success: function(data1){
						
						let data =JSON.parse(data1);
						
						$.each(data, function(){
							if(userid == username){
								$('#chat').append(
										'<li class="me">'
										+'<div class="entete">'
										+'<h3>10:12AM, Today</h3>'
										+'&nbsp;'
										+'<h2> Vincent</h2>'
										+'<span class="status blue"></span>'
										+'</div>'
										+'<div class = "message">'+ str + '</div>' 
										+ '</li>'
										);	
								} else{
									$('#chat').append(
											'<li class="you">'
											+'<div class="entete">'
											+'<h3>10:12AM, Today</h3>'
											+'&nbsp;'
											+'<h2> Vincent</h2>'
											+'<span class="status blue"></span>'
											+'</div>'
											+'<div class = "message">'+ str + '</div>' 
											+ '</li>'
											);	
									
								}
							
						});
					}
					
				});
			}, */
			closeMessage: function(str) {
				$('#divChatData').append('<div>' + '연결 끊김 : ' + str + '</div>');
			},
			disconnect: function() {
				this._socket.close();
			},
			_initSocket: function() {
				this._socket = new SockJS(this._url);
				this._socket.onmessage = function(evt) {
					webSocket.receiveMessage(evt.data);
				};
				this._socket.onclose = function(evt) {
					webSocket.closeMessage(evt.data);
				}
			},
			_sendMessage: function(str) {
				this._socket.send(str);
			}
		};
	</script>	
	<script type="text/javascript">
		$(document).ready(function() {
			webSocket.init({ url: '<c:url value="/chat" />' });			
		});
	</script>
</head>
<body>


    <div id="container" width="100%" height="100%">
        <aside>
            <header>
                <input type="text" placeholder="search">
            </header>
        </aside>
        <main>
            <ul id="chat">
                <li class="you">
                   <div class="entete">
                        <span class="status green"></span>
                        <h2>Vincent</h2>
                        <h3>10:12AM, Today</h3>
                    </div>
                    <div class="triangle"></div>
                    <div class="message">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.
                    </div>
                </li>
                <li class="me">
                    <div class="entete">
                        <h3>10:12AM, Today</h3>
                        <h2>Vincent</h2>
                        <span class="status blue"></span>
                    </div>
                    <div class="message">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.
                    </div>
                </li>
            </ul>
            <footer>
                <textarea id="message" placeholder="Type your message" onkeypress="if(event.keyCode==13){webSocket.sendChat();}"></textarea>
                <!-- <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/1940306/ico_picture.png" alt="">
                <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/1940306/ico_file.png" alt=""> -->
                <button id="chat-send" class="msg_send_btn" type="button" onclick="webSocket.sendChat()"><i class="fa fa-paper-plane"
                  aria-hidden="true"></i></button>
            </footer>
        </main>
    </div>

<!-- 
			<div style="overflow:auto; width: 800px; height: 700px; padding: 10px; border: solid 1px #e1e3e9;">
		<div id="divChatData"></div>
	</div>
 -->

         <!--  <div class="type_msg">
            <div class="input_msg_write">
              <input id="message" type="text" class="write_msg" placeholder="Type a message" onkeypress="if(event.keyCode==13){webSocket.sendChat();}" />
              <button id="chat-send" class="msg_send_btn" type="button" onclick="webSocket.sendChat()"><i class="fa fa-paper-plane"
                  aria-hidden="true"></i></button>
            </div>
          </div> -->
        
	
</body>
</html>