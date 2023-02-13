package kr.or.gadget.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.gadget.dao.UsersDao;
import kr.or.gadget.dto.Users;

@Service
public class UserService {

	private SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	//사용자 생성
	public void createUser(Users users) {
		UsersDao userdao = sqlsession.getMapper(UsersDao.class);
		userdao.createUser(users);
	};
	//특정 워크스페이스에 포함된 사용자 검색
	public List<Users> selectUserByWspace(int spaceid) {
		UsersDao userdao = sqlsession.getMapper(UsersDao.class);
		//List<Users> list = userdao.selectUserByWspace(spaceid);
		List<Users> list = userdao.selectUserByWspace(1);
		return list;
	};
	//사용자 검색
	public Users selectUserByUserid(String userid) {
		UsersDao userdao = sqlsession.getMapper(UsersDao.class);
		return userdao.selectUserByUserid(userid);
	};
	//사용자 정보 수정
	public int updateUser(Users users) {
		UsersDao userdao = sqlsession.getMapper(UsersDao.class);
		return userdao.updateUser(users);
	};
	//사용자 삭제
	public int deleteUser(String userid) {
		UsersDao userdao = sqlsession.getMapper(UsersDao.class);
		return userdao.deleteUser(userid);
	};
}
