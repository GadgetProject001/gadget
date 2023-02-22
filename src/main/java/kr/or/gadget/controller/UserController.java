package kr.or.gadget.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.xdevapi.JsonParser;

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
	@PostMapping(value="/")
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
	
	@PostMapping(value="/googleLogin")
	public Users googleLogin(@RequestBody Users users) {
		
//		System.out.println(param);
		
		System.out.println("users : " + users);
		
		BufferedReader in  = null;
		InputStream is = null;
		InputStreamReader isr = null;
		JSONParser jsonParser = new JSONParser();
        
		String userid = null;
		String username = null;
		String email = null;
		String imgurl = null;
		String logintype = null;
		
		Users user = new Users();
		
		try {
//			String idToken = param.split(":")[1];
			
			String url = "https://oauth2.googleapis.com/tokeninfo";
            url += "?id_token="+users.getToken();
            
            System.out.println(url);
			
			URL gUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) gUrl.openConnection();

			is = conn.getInputStream();
			isr = new InputStreamReader(is, "UTF-8");
			in = new BufferedReader(isr);

			JSONObject jsonObj = (JSONObject)jsonParser.parse(in);

			userid = jsonObj.get("sub").toString();
			username = jsonObj.get("name").toString();
			email = jsonObj.get("email").toString();
			imgurl = jsonObj.get("picture").toString();
			logintype = jsonObj.get("iss").toString();
			
			System.out.println(userid);
			System.out.println(username);
			System.out.println(email);
			System.out.println(imgurl);
			System.out.println(logintype);
			
			user = new Users();
			user.setUserid(userid);
			user.setImgurl(imgurl);
			user.setUsername(username);
			user.setLogintype(logintype);
			user.setEmail(email);
			
			System.out.println(user);
		    userservice.insertUser(user);	
			userservice.updateUserLastDate(userid);
			
			return user;
		}catch( IOException | ParseException e) {
			System.out.println(e);
			return user;
		}
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<List<Users>> selectUsers(){
		List<Users> users = new ArrayList<Users>();
		try {
			System.out.println("정상실행");
			users = userservice.selectUsers();
			return new ResponseEntity<List<Users>>(users,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Users>>(users,HttpStatus.BAD_REQUEST);
		}
	}
}
