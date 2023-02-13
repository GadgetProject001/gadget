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
import kr.or.gadget.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodosController {
	
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
	
	/* 	 public TodoContentJoinState selectTodoContent(TodoContentJoinState todoContentJoinState){
	   TodoDao todoDao = sqlsession.getMapper(TodoDao.class);
	   return todoDao.selectTodoContent(todoContentJoinState);
	 }; */
	
	@RequestMapping(value="/todoContent/{contentid}", method=RequestMethod.GET)
	public TodoContentJoinState selectTodoContent(@PathVariable int contentid) {
		return todoService.selectTodoContent(contentid);
	}
	
	@RequestMapping(value="/todoContent", method=RequestMethod.POST)
	public int insertTodoContent(@RequestBody TodoContentJoinState todoContentJoinState) {
		System.out.println(todoContentJoinState);
		return todoService.insertTodoContent(todoContentJoinState);
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


/* package kr.or.kosa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosa.dto.Emp;
import kr.or.kosa.service.EmpService;

@RestController
@RequestMapping("/emp")
public class EmpController {
	
    // controller는 service 의존합니다. 
	private EmpService empservice;

	@Autowired
	public void setEmpservice(EmpService empservice) {
		this.empservice = empservice;
	}

//	@RequestMapping(value="", method=RequestMethod.GET)
//    public List<Emp> empList(){
//    	return empservice.selectAllEmpList();
//    }
	
	// 전체조회
	@RequestMapping(value="", method=RequestMethod.GET)
    public ResponseEntity<List<Emp>> empList(){
         List<Emp> list = new ArrayList<Emp>();
         
         try {
			System.out.println("정상실행");
			list = empservice.selectAllEmpList();
			return new ResponseEntity<List<Emp>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Emp>>(list, HttpStatus.BAD_REQUEST);
		}
    }
	
	//조건조회 
	@RequestMapping(value="{empno}", method=RequestMethod.GET)
	public Emp emplistByempno(@PathVariable int empno) {
		System.out.println("empservice.selectByEmpno(empno) : " + empservice.selectByEmpno(empno));
		return empservice.selectByEmpno(empno);
	}
	
	// 삽입 (POST)
	// 데이터 (empno=3000, ename=아무개, sal=10) 서버로 전달 
	// http://localhost:8080/kosa/emp >> data (empno=3000, ename=아무개, sal=10)
	
//	@RequestMapping(value="", method=RequestMethod.POST)
//	public ResponseEntity<String> insert(@RequestBody Emp emp) {
//		try {
//			System.out.println("insert 실행");
//			empservice.insert(emp);
//			return new ResponseEntity<String>("insert success", HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<String>("insert fail", HttpStatus.BAD_REQUEST);
//		} finally {
//			
//		}
//	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<List<Emp>> insert(@RequestBody Emp emp) {
			System.out.println("insert 실행");
			empservice.insert(emp);
			return empList();
	}
	
	
	// 수정 
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<List<Emp>> update(@RequestBody Emp emp) {
		 empservice.update(emp);
         return empList();
	}
	
	// 삭제 
	@RequestMapping(value="{empno}", method=RequestMethod.DELETE)
	public ResponseEntity<List<Emp>> delete(@PathVariable(name="empno") int empno) {
		
			System.out.println("delete 실행");
			empservice.delete(empno);
			return empList();
	}
	
}
 */
