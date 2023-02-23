package kr.or.gadget.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.gadget.dao.TodoDao;
import kr.or.gadget.dto.State;
import kr.or.gadget.dto.TodoContent;
import kr.or.gadget.dto.TodoContentJoinState;
import kr.or.gadget.dto.TodoContentJoinStateJoinUsers;
import kr.or.gadget.service.TodoService;

@RestController
@RequestMapping("/todos")
public class todosController {
	
	private TodoService todoService;
	
	@Autowired
	public void setTodoService(TodoService todoService) {
		this.todoService = todoService;
	}
	
	
	/* 	public List<TodoContent> selectTodoList (int spaceid) {
		TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
	    return todoDao.selectTodoList(spaceid);
	}; */
	
	/* TodoList 불러오기 
	 * http://localhost:8080/gadget/todos/1*/
	@RequestMapping(value="{spaceid}", method=RequestMethod.GET)
	public List<TodoContent> selectTodoList(@PathVariable int spaceid) {
		return todoService.selectTodoList(spaceid);
	}
	
	/* 	public int updateTodoState (TodoContent todoContent){
		TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
		return todoDao.updateTodoState(todoContent);
	}; */
	
	/* Todo State 변경하기 
	 * http://localhost:8080/gadget/todos/move */
	@RequestMapping(value="/move", method=RequestMethod.PATCH)
	public int updateTodoState(@RequestBody TodoContentJoinState todoContentJoinState) {
		return todoService.updateTodoState(todoContentJoinState);
	}
	
	
	/* 	 public int updateStateIndex(State state){
	   TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
	   return todoDao.updateStateIndex(state);
	 }; */
	/* State index 변경하기
	 * http://localhost:8080/gadget/todos/order */
	@RequestMapping(value="/order", method=RequestMethod.PATCH)
	public int updateStateIndex(@RequestBody State state) {
		return todoService.updateStateIndex(state);
	}
	
	@RequestMapping(value="/order/{spaceid}", method=RequestMethod.GET)
//	public State selectTodoOrder(@PathVariable int spaceid) {
	public String selectTodoOrder(@PathVariable int spaceid) {
		return todoService.selectTodoOrder(spaceid);
	}
	
	/* 	 public TodoContentJoinState selectTodoContent(TodoContentJoinState todoContentJoinState){
	   TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
	   return todoDao.selectTodoContent(todoContentJoinState);
	 }; */
	
	@RequestMapping(value="/todoContent/{contentid}", method=RequestMethod.GET)
	public TodoContentJoinStateJoinUsers selectTodoContent(@PathVariable int contentid) {
		System.out.println(todoService.selectTodoContent(contentid));
		return todoService.selectTodoContent(contentid);
	}
	
	@RequestMapping(value="/todoContent", method=RequestMethod.POST)
	public int insertTodoContent(@RequestBody TodoContentJoinStateJoinUsers todoContentJoinStateJoinUsers) {
		System.out.println(todoContentJoinStateJoinUsers);
		return todoService.insertTodoContent(todoContentJoinStateJoinUsers);
	}
	
	/* 	 public int updateTodoContent(TodoContentJoinState todoContentJoinState){
		TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
		return todoDao.updateTodoContent(todoContentJoinState);
	 }; */
	
	@RequestMapping(value="/todoContent", method=RequestMethod.PATCH)
	public int updateTodoContent(@RequestBody TodoContentJoinState todoContentJoinState) {
		return todoService.updateTodoContent(todoContentJoinState);
	}
}
