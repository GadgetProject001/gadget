package kr.or.gadget.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.gadget.dao.ChatDao;
import kr.or.gadget.dto.Chat;
import kr.or.gadget.dto.Users;
import lombok.Setter;

@Service
public class ChatService {


	@Autowired
	private SqlSession sqlsession;
	
	public List<Chat> Chatlist(int spaceid){
		
		List<Chat> result = null;
		try {	
			ChatDao chatdao = sqlsession.getMapper(ChatDao.class);
			result = chatdao.chatList(spaceid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	

	public String getNameByUserId(String userid) throws ClassNotFoundException, SQLException {
		//db작업
		System.out.println(userid);
		String username = null;
		Users user = null;
		try {
			ChatDao chatdao = sqlsession.getMapper(ChatDao.class);
			user = chatdao.getNameByUserId(userid);
			username = user.getUsername();
		} catch (Exception e) {
			e.printStackTrace();
			username = "실패";
		}
		return username; 
	}
	public boolean insertChat(Chat chat) {
		boolean insertOk = false;
		try {
			ChatDao chatdao = sqlsession.getMapper(ChatDao.class);
			insertOk = (chatdao.insertchat(chat) >0) ? true : false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertOk;
	}
}
