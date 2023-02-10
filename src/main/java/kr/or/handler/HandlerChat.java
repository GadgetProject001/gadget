package kr.or.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class HandlerChat extends TextWebSocketHandler {
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	//채팅방 , 채팅방에 참여중인 session List
	private Map<String, WebSocketSession> map = new HashMap<String, WebSocketSession>();
	
	private Map<WebSocketSession,String> wjqthr = new HashMap<WebSocketSession, String>();
	
	private Map<WebSocketSession,String> id = new HashMap<WebSocketSession, String>();
	
	//클라이언트가 서버로 연결 처리
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		/*
		 * 1번방에서 접속했다
		 
		 * 만약 map 안에 1이 없으면
		 * private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
		 * map.추가("1", sessionList);
		 * 
		 * map.get("1").add(session);
		 * 
		 * 1번방이 있을 때 1번방에서 접속을 하면
		 * map.get("1").add(session);
		 * */
		
		//채팅방에 접속한 사용자 세션을 리스트에 저장
		sessionList.add(session);
		//모든 세션에 채팅 전달
		for(int i = 0; i<sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			s.sendMessage(new TextMessage(session.getId() + "님이 입장 했습니다."));
		}
	}
	
	// 클라이언트가 서버로 메서지 전송 처리
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		// "누가/메세지"
		//"id/message"
		
		// string id = id
		// string message = message
		
		// db 처리 하고
		
		// 성공하면 메세지 채팅방 전체에 보내고
		// 실패하면 보낸 사람한테만 (파라미터로 받은 session) 실패했다고 보내고
		
		//모든 세션에 채팅 전달
		for (int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			s.sendMessage(new TextMessage(session.getId() + " : " + message.getPayload()));
	}
}
	// 클라이언트가 연결을 끊음 처리
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

			// 채팅방에서 퇴장한 사용자 세션을 리스트에서 제거
			sessionList.remove(session);
			
			// 모든 세션에 채팅 전달
			for (int i = 0; i < sessionList.size(); i++) {
				WebSocketSession s = sessionList.get(i);
				s.sendMessage(new TextMessage(session.getId() + "님이 퇴장 했습니다."));
			}

		}
	}