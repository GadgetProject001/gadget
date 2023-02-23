package kr.or.gadget.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.gadget.dao.CalendarDao;
import kr.or.gadget.dao.TodoDao;
import kr.or.gadget.dto.Calendar;
import kr.or.gadget.dto.State;
import kr.or.gadget.dto.TodoContent;
import kr.or.gadget.dto.TodoContentJoinState;
import kr.or.gadget.dto.TodoContentJoinStateJoinUsers;

@Service
public class TodoService {
	
	private SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	/* 	public List<Calendar> calendarList(int spaceid){
		CalendarDao caldao = sqlsession.getMapper(CalendarDao.class);
		//List<Calendar> list = caldao.calendarList(spaceid);
		List<Calendar> list = caldao.calendarList(spaceid);
		return list;
	} */
	
	public List<TodoContent> selectTodoList (int spaceid) {
		TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
	    return todoDao.selectTodoList(spaceid);
	};
	
	public int updateTodoState (TodoContentJoinState todoContentJoinState){
		TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
		return todoDao.updateTodoState(todoContentJoinState);
	};
	
	 public int updateStateIndex(State state){
	   TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
	   return todoDao.updateStateIndex(state);
	 };
	 
	 public TodoContentJoinStateJoinUsers selectTodoContent(int contentid){
	   TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
	   return todoDao.selectTodoContent(contentid);
	 };
	   
	 public int insertTodoContent(TodoContentJoinStateJoinUsers todoContentJoinStateJoinUsers){
	    TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
	    return todoDao.insertTodoContent(todoContentJoinStateJoinUsers);
	 };
	 
	 public int updateTodoContent(TodoContentJoinState todoContentJoinState){
		TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
		return todoDao.updateTodoContent(todoContentJoinState);
	 };
	 
//	 public State selectTodoOrder(int spaceid) {
	 public String selectTodoOrder(int spaceid) {
		TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
		return todoDao.selectTodoOrder(spaceid);
	 }

}
