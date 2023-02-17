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

	// 채팅방 , 채팅방에 참여중인 session List
	private Map<String, List<WebSocketSession>> map = new HashMap<String, List<WebSocketSession>>();
	
	// 세션이 어떤 방(워크스페이스)에 연결되어있는지
	private Map<WebSocketSession,String> connect = new HashMap<WebSocketSession, String>();
	
	//어떤 세션이 어떤 아이디를 가지는지
	private Map<WebSocketSession,String> id = new HashMap<WebSocketSession, String>();
	
	/*
	 * private Map<String, HashMap<String, WebSocketSession>> usermap =
	 * SessionMaps.getUserMap();
	 */

	// 클라이언트가 서버로 연결 처리
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		/*
		 * // 입장한 채팅방 이름 꺼내와 변수에 저장 String chatname = getCurrentChatRoom(session);
		 * System.out.println("입장한 채팅방 = " + chatname);
		 * System.out.println("sessionid : " + session.getId()); if
		 * (SessionMaps.getUserMap().containsKey(chatname)) { // 기존에 존재해 Map에 저장되어 있었다면,
		 * usermap.get(chatname).put(session.getId(), session); // 클라이언트 session값 저장
		 * System.out.println("채팅방 존재했음");
		 * 
		 * } else { System.out.println("새로 생성된 채팅방"); // 채팅방이 새로 생성되었다면 Map<String,
		 * WebSocketSession> list = new HashMap<String, WebSocketSession>();
		 * list.put(session.getId(), session); // 클라이언트의 sessionId와 session 객체를 Map에 저장한
		 * 후 usermap.put(chatname, (HashMap<String, WebSocketSession>) list); //
		 * usermap에 Put함으로써 새로운 채팅방 생성 }
		 */

		/*
		 * 1번방에서 접속했다
		 * 
		 * 만약 map 안에 1이 없으면 private List<WebSocketSession> sessionList = new
		 * ArrayList<WebSocketSession>(); map.추가("1", sessionList);
		 * 
		 * map.get("1").add(session);
		 * 
		 * 1번방이 있을 때 1번방에서 접속을 하면 map.get("1").add(session);
		 */
		// 채팅방에 접속한 사용자 세션을 리스트에 저장
//		sessionList.add(session);
		// 모든 세션에 채팅 전달
		for (int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			s.sendMessage(new TextMessage(session.getId() + "님이 입장 했습니다."));
		}

		/*
		 * String userid = getNickName(session); String inform = "알림|" + userid +
		 * "님이 입장하였습니다."; System.out.println(inform); TextMessage msg = new
		 * TextMessage(inform);
		 * 
		 * for (Map.Entry m : usermap.get(chatname).entrySet()) { // 메시지가 입력된 채팅방에 있는
		 * 클라이언트에게만 메시지 전송 WebSocketSession sess = (WebSocketSession) m.getValue();
		 * sess.sendMessage(msg); }
		 */

	}

	// 클라이언트가 서버로 메서지 전송 처리
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		// 입장/id/spaceid
		// 메세지/안녕하세요
		String msg = message.getPayload();
		String [] msgs = msg.split("/");
		
		if(msgs[0].equals("입장")) {
			map.get(msgs[2]).add(session); //3번방에 세션추가
			id.put(session,msgs[1]);		// 어떤 세션이 어떤 아이디를 가지고 있는지 기록
			connect.put(session,msgs[2]);//세션이 몇번방에 있는지 기록
		} else if(msgs[0].equals("메세지")) {
			String roomid = connect.get(session);
			String id_str = id.get(session); 
			
			
			// id_str
			// db에서 이것저것 가져온다음에
			// 가져온 것들로 채팅 insert
			// insert 결과가 성공 > service.insertChat() > 결과가 true / 1
			for(WebSocketSession s : map.get(roomid)) {
				if(s == session) {
					/*
					 * '<li class="me">' +'<div class="entete">' +'<h3>10:12AM, Today</h3>'
					 * +'&nbsp;' +'<h2> Vincent</h2>' +'<span class="status blue"></span>' +'</div>'
					 * +'<div class = "message">'+ str + '</div>' + '</li>'
					 */
				} else {
					s.sendMessage(new TextMessage( id_str + " : " + message.getPayload()));
				}
			}
			// 결과가 실패면
			
			
			// session.sendMessage(실패했다고 알려주기)
			
		}
		
		
		// "누가/메세지"
		// "id/message"

		// string id = id
		// string message = message

		// db 처리 하고

		// 성공하면 메세지 채팅방 전체에 보내고
		// 실패하면 보낸 사람한테만 (파라미터로 받은 session) 실패했다고 보내고

		// 메세지가 입력된 채팅방에 있는 클라이언트 에게만 메세지 전달
		/*
		 * String chatname = getCurrentChatRoom(session);
		 * System.out.println("메시지가 입력된 채팅방 :" + chatname);
		 * System.out.println("message : " + message.getPayload());
		 * 
		 * for (Map.Entry m : usermap.get(chatname).entrySet()) { // 메시지가 입력된 채팅방에 있는
		 * 클라이언트에게만 메시지 전송 WebSocketSession sess = (WebSocketSession) m.getValue();
		 * sess.sendMessage(message); }
		 */

		
		  //모든 세션에 채팅 전달 
		  for (int i = 0; i < sessionList.size(); i++) {
		  WebSocketSession s = sessionList.get(i); s.sendMessage(new
		  TextMessage(session.getId() + " : " + message.getPayload())); }
		 
	}

	// 클라이언트가 연결을 끊음 처리
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		// 입장한 채팅방 이름 꺼내와 변수 저장
		String chatname = getCurrentChatRoom(session);
		String userid = getNickName(session);
		String inform = "알림|" + userid + "님이 퇴장하였습니다.";
		TextMessage msg = new TextMessage(inform);
		
		  // 채팅방에서 퇴장한 사용자 세션을 리스트에서 제거
			sessionList.remove(session);
		 
		/*
		 * usermap.get(chatname).remove(session.getId()); // 채팅방에서 클라이언트라 접속을 끊으면, 참여중인
		 * 목록에서 session을 삭제한 후 session.close(); System.out.println(getNickName(session)
		 * + "나갔습니다.");
		 */
		
		  // 모든 세션에 채팅 전달 
		 for (int i = 0; i < sessionList.size(); i++) {
		  WebSocketSession s = sessionList.get(i); s.sendMessage(new
		  TextMessage(session.getId() + "님이 퇴장 했습니다.")); }
		 
		/*
		 * for (Map.Entry m : usermap.get(chatname).entrySet()) { // 메시지가 입력된 채팅방에 있는
		 * 클라이언트에게만 메시지 전송 WebSocketSession sess = (WebSocketSession) m.getValue();
		 * sess.sendMessage(msg); }
		 */

	}

	// 채팅방의 정보를 받아오는 함수
	public String getCurrentChatRoom(WebSocketSession session) {
		Map<String, Object> map = session.getAttributes();

		return (String) map.get("");
	}

	// 채팅방의 정보를 받아오는 함수
	public String getNickName(WebSocketSession session) {
		Map<String, Object> map = session.getAttributes();

		return (String) map.get("nickname");
	}

}