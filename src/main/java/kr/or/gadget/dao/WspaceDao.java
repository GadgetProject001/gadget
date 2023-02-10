package kr.or.gadget.dao;

import kr.or.gadget.dto.Wspace;

public interface WspaceDao {
	//워크스페이스 생성
	void createspace(Wspace wspace);
	//워크스페이스 이름 수정
	int updatespace(Wspace wspace);
	//워크스페이스 삭제
	int deletespace(int spaceid);
}
