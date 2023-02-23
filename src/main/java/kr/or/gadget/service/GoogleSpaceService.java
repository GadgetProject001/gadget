package kr.or.gadget.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.gadget.dao.GoogleSpaceDao;
import kr.or.gadget.dto.GoogleSpace;
import lombok.Setter;

@Service
public class GoogleSpaceService {
	
	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlsession;
	
	//특정 종류의 리스트 항목 출력
	public List<GoogleSpace> selectListByKind(GoogleSpace googleSpace) {
		GoogleSpaceDao dao = sqlsession.getMapper(GoogleSpaceDao.class);
		List<GoogleSpace> list = dao.selectListByKind(googleSpace);
		return list;
	};
	//추가
	public void insertGspace(GoogleSpace googleSpace) {
		GoogleSpaceDao dao = sqlsession.getMapper(GoogleSpaceDao.class);
		dao.insertGspace(googleSpace);
	};
	//수정
	public int updateGspace(GoogleSpace googleSpace) {
		GoogleSpaceDao dao = sqlsession.getMapper(GoogleSpaceDao.class);
		return dao.updateGspace(googleSpace);
	};
	//삭제
	public int deleteGspace(int gspaceid) {
		GoogleSpaceDao dao = sqlsession.getMapper(GoogleSpaceDao.class);
		return dao.deleteGspace(gspaceid);
	};

}
