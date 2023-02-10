package kr.or.gadget.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.gadget.dto.Calendar;
import kr.or.gadget.service.CalendarService;


@RestController
@RequestMapping("/calendar/*")
public class CalendarController {
	
	
	private CalendarService service;
	
	@Autowired
	public void setService(CalendarService service) {
		this.service = service;
	}
	
	//특정 워크스페이스의 캘린더정보 전부 가져오기
	@GetMapping(value = "/{spaceid}/list")
	public ResponseEntity<List<Calendar>> calendarList(@PathVariable("spaceid")int spaceid) {
		List<Calendar> calendar = new ArrayList<Calendar>();
		try {
			System.out.println("정상실행");
			calendar = service.calendarList(spaceid);
			return new ResponseEntity<List<Calendar>>(calendar,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Calendar>>(calendar,HttpStatus.BAD_REQUEST);
		}
	}
	
	//일정추가
	@PostMapping(value = "/{spaceid}")
	public ResponseEntity<List<Calendar>> insertCalendar(@PathVariable("spaceid")int spaceid, @RequestBody Calendar calendar){
		List<Calendar> list = new ArrayList<Calendar>();
		try {
			calendar.setSpaceid(spaceid);
			System.out.println("spaceid 입력");
			service.insertCalendar(calendar);
			list = service.calendarList(spaceid);
			return new ResponseEntity<List<Calendar>>(list,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("입력실패");
			//e.printStackTrace();
			return new ResponseEntity<List<Calendar>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	//일정수정
	@PatchMapping(value = "/{spaceid}")
	public ResponseEntity<List<Calendar>> updateCalendar(@PathVariable("spaceid")int spaceid, @RequestBody Calendar calendar){
		List<Calendar> list = new ArrayList<Calendar>();
		try {
			System.out.println("update 실행");
			calendar.setSpaceid(spaceid);
			System.out.println(calendar.toString());
			service.updateCalendar(calendar);
			list = service.calendarList(spaceid);
			return new ResponseEntity<List<Calendar>>(list,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("update 실패");
			return new ResponseEntity<List<Calendar>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	//일정삭제
	@DeleteMapping(value = "/{spaceid}/{id}")
	public ResponseEntity<List<Calendar>> deleteCalendar(@PathVariable("spaceid")int spaceid, @PathVariable("id")int id){
		List<Calendar> list = new ArrayList<Calendar>();
		try {
			System.out.println("delete 실행");
			service.deleteCalendar(id);
			list = service.calendarList(spaceid);
			return new ResponseEntity<List<Calendar>>(list,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("delete 실패");
			return new ResponseEntity<List<Calendar>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	//특정 워크스페이스의 캘린더정보 전부 가져오기
	@GetMapping(value = "/id")
	public ResponseEntity<Integer> selectId() {
		int id = 0;
		try {
			System.out.println("id 가져오기");
			id = service.selectId();
			System.out.println("id: " + id);
			return new ResponseEntity<Integer>(id,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(id,HttpStatus.BAD_REQUEST);
		}
	}
 }
