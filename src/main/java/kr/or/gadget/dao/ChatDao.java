package kr.or.gadget.dao;

import java.util.List;

import kr.or.gadget.dto.Chat;

public interface ChatDao {
	
	//특정 워크스페이스(채팅방)의 대화내용 가져오기
	List<Chat> chatList(int spaceid);
	
	//대화내용 저장하기
	int insertchat(Chat chat);
	
	
}
