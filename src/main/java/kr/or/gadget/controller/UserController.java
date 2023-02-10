package kr.or.gadget.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.gadget.dto.Users;
import kr.or.gadget.service.UserService;

@RestController
@RequestMapping("/user/*")
public class UserController {
	
	private UserService userservice;

	@Autowired
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	
	//사용자 검색
	@GetMapping(value = "/mypage/{userid}")
	public ResponseEntity<Users> selectUserByUserid(@PathVariable("userid")String userid){
		Users users = new Users();
		try {
			System.out.println("정상실행");
			users = userservice.selectUserByUserid(userid);
			return new ResponseEntity<Users>(users,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Users>(users,HttpStatus.BAD_REQUEST);
		}
	}
	
	//특정 워크스페이스에 포함된 사용자 검색
	@GetMapping(value = "/wspace/{spaceid}")
	public ResponseEntity<List<Users>> selectUserByWspace(@PathVariable("spaceid")int spaceid){
		List<Users> list = new ArrayList<Users>();
		try {
			System.out.println("정상실행");
			list = userservice.selectUserByWspace(spaceid);
			return new ResponseEntity<List<Users>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Users>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	//사용자 생성
	@PostMapping(value="")
	public ResponseEntity<Users> createUser(@RequestBody Users users) {
		Users user = null;
		try {
			System.out.println("insert 실행");
			userservice.createUser(users);
			user = userservice.selectUserByUserid(users.getUserid());
			return new ResponseEntity<Users>(user,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("insert 실패");
			return new ResponseEntity<Users>(user, HttpStatus.BAD_REQUEST);
		}
	}
	
	//사용자 정보 수정
	@PutMapping(value="")
	public ResponseEntity<Users> updateUser(@RequestBody Users users){
		Users user = new Users();
		try {
			System.out.println("정상실행");
			userservice.updateUser(users);
			user = userservice.selectUserByUserid(user.getUserid());
			return new ResponseEntity<Users>(user,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Users>(user,HttpStatus.BAD_REQUEST);
		}
	}
	
	//사용자 삭제
	@DeleteMapping(value = "{userid}")
	public void deleteUser(@RequestBody String userid) {
		try {
			System.out.println("delete 실행");
			userservice.deleteUser(userid);
			System.out.println("delete 완료");
		} catch (Exception e) {
			System.out.println("delete 실패");
		}
	};
}
