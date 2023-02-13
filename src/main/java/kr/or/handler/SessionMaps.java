package kr.or.handler;

import java.util.HashMap;

import org.springframework.web.socket.WebSocketSession;

public class SessionMaps {
	private static HashMap<String, HashMap<String,WebSocketSession>> usermap =  new HashMap();
	
	public static HashMap getUserMap() {
		return usermap;
	}
	
}
