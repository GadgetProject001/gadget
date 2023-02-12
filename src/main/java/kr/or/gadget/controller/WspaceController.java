package kr.or.gadget.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.gadget.dto.Side;
import kr.or.gadget.dto.Wspace;
import kr.or.gadget.service.WspaceService;

@RestController
@RequestMapping("/workspace/*")
public class WspaceController {

	private WspaceService service;

	@Autowired
	public void setService(WspaceService service) {
		this.service = service;
	}
	
//	@GetMapping()
//	public ResponseEntity<> createspace(Space space){
//		
//	}
	
	@GetMapping(value = "/list/{userid}")
	public ResponseEntity<List<Wspace>> selectWspace(@PathVariable("userid") String userid){
		List<Wspace> list = new ArrayList<Wspace>();
		try {
			System.out.println("Wspace 리스트 출력");
			list = service.selectWspace(userid);
			return new ResponseEntity<List<Wspace>>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Wspace>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/{spaceid}/side")
	public ResponseEntity<List<Side>> selectSideByWspaceid(@PathVariable("spaceid") int spaceid) {
		List<Side> list = new ArrayList<Side>();
		try {
			System.out.println("Side 리스트 출력");
			Wspace wspace = new Wspace();
			wspace.setSpaceid(spaceid);
			list = service.selectSideByWspaceid(wspace);
			return new ResponseEntity<List<Side>>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Side>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/named/{userid}")
	public ResponseEntity<List<Wspace>> updateSpaceName(@PathVariable("userid") String userid,@RequestBody Wspace wspace) {
		List<Wspace> list = new ArrayList<Wspace>();
		try {
			System.out.println("Wspace 이름변경");
			service.updateSpaceName(wspace);
			list = service.selectWspace(userid);
			return new ResponseEntity<List<Wspace>>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Wspace>>(list,HttpStatus.BAD_REQUEST);
		}
	}
}
