package kr.or.gadget.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.gadget.dao.WspaceDao;
import lombok.Setter;

public class WspaceService {

	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlsession;
	
	public void createspace(Map<String, String> map) {
		WspaceDao dao = sqlsession.getMapper(WspaceDao.class);
		dao.createspace(map);
	}
}
