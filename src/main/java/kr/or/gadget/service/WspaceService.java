package kr.or.gadget.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.gadget.dao.WspaceDao;
import kr.or.gadget.dto.Side;
import kr.or.gadget.dto.Space;
import kr.or.gadget.dto.WorkSpace;
import kr.or.gadget.dto.Wspace;
import lombok.Setter;

@Service
public class WspaceService {

	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlsession;
	
	public void createspace(Space space) {
		WspaceDao dao = sqlsession.getMapper(WspaceDao.class);
		dao.createspace(space);
	}
	
	public List<Wspace> selectWspace(String userid) {
		WspaceDao dao = sqlsession.getMapper(WspaceDao.class);
		return dao.selectWspace(userid);
	}
	
	public List<Side> selectSideByWspaceid(Wspace wspace) {
		WspaceDao dao = sqlsession.getMapper(WspaceDao.class);
		return dao.selectSideByWspaceid(wspace);
	}
	
	public int updateSpaceName(Wspace wspace) {
		WspaceDao dao = sqlsession.getMapper(WspaceDao.class);
		return dao.updateSpaceName(wspace);
	}
	
	public int createWorkSpace(WorkSpace workSpace) {
		WspaceDao dao = sqlsession.getMapper(WspaceDao.class);
		return dao.createWorkSpace(workSpace);
	}
	
	public List<WorkSpace> selectWorkSpaceByUserId(String userid) {
		WspaceDao dao = sqlsession.getMapper(WspaceDao.class);
		return dao.selectWorkSpaceByUserId(userid);
	}
}
