package kr.or.gadget.dao;

import java.util.List;

import kr.or.gadget.dto.GoogleSpace;

public interface GoogleSpaceDao {
	//특정 종류의 리스트 항목 출력
	List<GoogleSpace> selectListByKind(GoogleSpace googleSpace);
	//추가
	void insertGspace(GoogleSpace googleSpace);
	//수정
	int updateGspace(GoogleSpace googleSpace);
	//삭제
	int deleteGspace(int gspaceid);
}
