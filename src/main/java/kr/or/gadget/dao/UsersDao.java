package kr.or.gadget.dao;

import java.util.List;

import kr.or.gadget.dto.Users;

public interface UsersDao {
	//사용자 생성
	void createUser(Users users);
	//특정 워크스페이스에 포함된 사용자 검색
	List<Users> selectUserByWspace(int spaceid);
	//사용자 검색
	Users selectUserByUserid(String userid);
	//사용자 정보 수정
	int updateUser(Users users);
	//사용자 삭제
	int deleteUser(String userid);
	//사용자 비번 확인
	int selectPwUser(Users users);
}
