package kr.or.gadget.dao;

import java.util.List;

import kr.or.gadget.dto.Side;
import kr.or.gadget.dto.Space;
import kr.or.gadget.dto.Wspace;

public interface WspaceDao {
	//워크스페이스 생성
	void createspace(Space space);
	//사용자가 참여한 워크 스페이스 검색
	List<Wspace> selectWspace(String userid);
	//워크 스페이스 사이드 게시판 정보 가져오기
	List<Side> selectSideByWspaceid(Wspace wspace);
	//워크스페이스 이름 수정
	int updateSpaceName(Wspace wspace);
	//워크스페이스 삭제
//	int deleteSpace(int spaceid);
}
