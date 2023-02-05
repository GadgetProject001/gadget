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
		List<Calendar> list = caldao.calendarList(1);
		return list;
	}
	
	public void insertCalendar(Calendar calendar) {
		CalendarDao caldao = sqlsession.getMapper(CalendarDao.class);
		caldao.insertCalendar(calendar);
	}
	
	public int updateCalendar(Calendar calendar) {
		CalendarDao caldao = sqlsession.getMapper(CalendarDao.class);
		return caldao.updateCalendar(calendar);
	}
	
	public int deleteCalendar(int id) {
		CalendarDao caldao = sqlsession.getMapper(CalendarDao.class);
		return caldao.deleteCalendar(id);
	}
}
