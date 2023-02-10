package kr.or.gadget.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.gadget.dao.CalendarDao;
import kr.or.gadget.dto.Calendar;

@Service
public class CalendarService {
	
	private SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	public List<Calendar> calendarList(int spaceid){
		CalendarDao caldao = sqlsession.getMapper(CalendarDao.class);
		//List<Calendar> list = caldao.calendarList(spaceid);
		List<Calendar> list = caldao.calendarList(spaceid);
		return list;
	}
	
	public void insertCalendar(Calendar calendar) {
		CalendarDao caldao = sqlsession.getMapper(CalendarDao.class);
		caldao.insertCalendar(calendar);
	}
	
	public int updateCalendar(Calendar calendar) {
		CalendarDao caldao = sqlsession.getMapper(CalendarDao.class);
		try {
			return caldao.updateCalendar(calendar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteCalendar(int id) {
		CalendarDao caldao = sqlsession.getMapper(CalendarDao.class);
		return caldao.deleteCalendar(id);
	}
	
	public int selectId() {
		System.out.println("서비스 실행");
		CalendarDao caldao = sqlsession.getMapper(CalendarDao.class);
		return caldao.selectId();
	}
}
