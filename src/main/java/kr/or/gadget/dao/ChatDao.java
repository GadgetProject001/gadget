package kr.or.gadget.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.gadget.dto.Chat;
import kr.or.gadget.dto.Users;

public interface ChatDao {
	
	//특정 워크스페이스(채팅방)의 대화내용 가져오기
	public List<Chat> chatList(int spaceid) throws ClassNotFoundException, SQLException;
	
	//대화내용 저장하기
	public int insertchat(Chat chat) throws ClassNotFoundException, SQLException;
	
	//비교해서 username 가져오기
	public Users getNameByUserId(String userid) throws ClassNotFoundException, SQLException;
	
	
}
