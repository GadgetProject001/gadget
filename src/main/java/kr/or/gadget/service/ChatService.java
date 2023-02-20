package kr.or.gadget.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.gadget.dao.BoardDao;
import kr.or.gadget.dao.ChatDao;
import kr.or.gadget.dto.Chat;
import lombok.Setter;

@Service
public class ChatService {


	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlsession;
	
	public List<Chat> Chatlist(int spaceid){
		ChatDao chatdao = sqlsession.getMapper(ChatDao.class);
		return null;
	}
	
//	
//	public int insertchat(int ) {
//		
//		ChatDao chatdao = sqlsession.getMapper(chatDao.class);
//			
//	
//		
//	}
	
}
