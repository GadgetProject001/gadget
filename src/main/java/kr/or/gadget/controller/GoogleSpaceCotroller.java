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

import kr.or.gadget.dto.GoogleSpace;
import kr.or.gadget.service.GoogleSpaceService;

@RestController
@RequestMapping("/gspace/*")
public class GoogleSpaceCotroller {
	
	private GoogleSpaceService service;

	@Autowired
	public void setService(GoogleSpaceService service) {
		this.service = service;
	}
	
	//특정 종류의 리스트 항목 출력
	@GetMapping(value = "/{spaceid}/row/{kind}")
	public ResponseEntity<List<GoogleSpace>> selectListByKind(@PathVariable("spaceid") int spaceid, @PathVariable("kind") int kind) {
		List<GoogleSpace> list = new ArrayList<GoogleSpace>();
		GoogleSpace googleSpace = new GoogleSpace();
		try {
			googleSpace.setKind(kind);
			googleSpace.setSpaceid(spaceid);
			list = service.selectListByKind(googleSpace);
			return new ResponseEntity<List<GoogleSpace>>(list,HttpStatus.OK);
		} catch (Exception e) {
			//e.printStackTrace();
			return new ResponseEntity<List<GoogleSpace>>(list,HttpStatus.BAD_REQUEST);
		}
	};
	//특정 종류 페이지 출력
	@GetMapping(value = "/{gspaceid}")
	public ResponseEntity<GoogleSpace> selectListByKind(@PathVariable("gspaceid") int gspaceid) {
		GoogleSpace googleSpace = new GoogleSpace();
		try {
			googleSpace = service.selectByGspaceid(gspaceid);
			return new ResponseEntity<GoogleSpace>(googleSpace,HttpStatus.OK);
		} catch (Exception e) {
			//e.printStackTrace();
			return new ResponseEntity<GoogleSpace>(googleSpace,HttpStatus.BAD_REQUEST);
		}
	};
	//추가한 종류의 gspace 리스트로 반환 ==> 3줄로 출력하면 각종류당 axios로 각각 불러와야 함
	@PostMapping(value = "/row")
	public ResponseEntity<List<GoogleSpace>> insertGspace(@RequestBody GoogleSpace googleSpace) {
		List<GoogleSpace> list = new ArrayList<GoogleSpace>();
		try {
			service.insertGspace(googleSpace);
			list = service.selectListByKind(googleSpace);
			return new ResponseEntity<List<GoogleSpace>>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<GoogleSpace>>(list,HttpStatus.BAD_REQUEST);
		}
	};
	//수정
	@PutMapping(value = "/row")
	public ResponseEntity<List<GoogleSpace>> updateGspace(@RequestBody GoogleSpace googleSpace) {
		List<GoogleSpace> list = new ArrayList<GoogleSpace>();
		try {
			service.updateGspace(googleSpace);
			list = service.selectListByKind(googleSpace);
			return new ResponseEntity<List<GoogleSpace>>(list,HttpStatus.OK);
		} catch (Exception e) {
			//e.printStackTrace();
			return new ResponseEntity<List<GoogleSpace>>(list,HttpStatus.BAD_REQUEST);
		}
		
	};
	//삭제
	@DeleteMapping(value = "/{gspaceid}/row")
	public ResponseEntity<String> deleteGspace(@PathVariable("gspaceid") int gspaceid) {
		try {
			service.deleteGspace(gspaceid);
			return new ResponseEntity<String>("Success_Delete",HttpStatus.OK);
		} catch (Exception e) {
			//e.printStackTrace();
			return new ResponseEntity<String>("Fail_Delete",HttpStatus.BAD_REQUEST);
		}
	};
}
