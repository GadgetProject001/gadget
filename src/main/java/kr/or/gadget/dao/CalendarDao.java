package kr.or.gadget.dao;

import java.util.List;

import kr.or.gadget.dto.Calendar;


public interface CalendarDao {
	//특정 워크스페이스의 캘린더정보 전부 가져오기
	List<Calendar> calendarList(int spaceid);
	//일정추가
	void insertCalendar(Calendar calendar);
	//일정수정
	int updateCalendar(Calendar calendar);
	//일정삭제
	int deleteCalendar(int id);
	//id값 최상위 출력
	int selectId();
}
