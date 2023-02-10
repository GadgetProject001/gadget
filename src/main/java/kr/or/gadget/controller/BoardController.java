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

import kr.or.gadget.dto.Board;
import kr.or.gadget.dto.Criteria;
import kr.or.gadget.service.BoardService;

@RestController
@RequestMapping("/board/*")
public class BoardController {

	private BoardService service;
	
	@Autowired
	public void setService(BoardService service) {
		this.service = service;
	}
	
	//글목록 보기
	@GetMapping(value = "/{spaceid}/list/{bcodeid}")//http://localhost:8090/gadget/board/1/list/2?pageNum=1&amount=5
	public ResponseEntity<List<Board>> selectBoardList (@PathVariable("spaceid") int spaceid, @PathVariable("bcodeid") int bcodeid, Criteria cri){
		List<Board> list = new ArrayList<Board>();
		try {
			System.out.println("board 리스트 출력");
			cri.setBcodeid(bcodeid);
			cri.setSpaceid(spaceid);
			list = service.selectBoardList(cri);
			return new ResponseEntity<List<Board>>(list,HttpStatus.OK);
		} catch (Exception e) {
			//e.printStackTrace();
			return new ResponseEntity<List<Board>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	//특정 글 자세히 보기
	@GetMapping(value = "/{spaceid}/detail/{boardid}")
	public ResponseEntity<Board> selectBoardByid(@PathVariable("spaceid") int spaceid, @PathVariable("boardid") int boardid){
		Board board = new Board();
		try {
			System.out.println("board 자세히");
			board = service.selectBoardByid(boardid);
			return new ResponseEntity<Board>(board,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Board>(board,HttpStatus.BAD_REQUEST);
		}
	}
	
	//글쓰기 + 파일첨부(예정)
	@PostMapping(value = "/{spaceid}/detail")
	public ResponseEntity<List<Board>> writeBoard(@PathVariable("spaceid") int spaceid, @RequestBody Board board) {
		List<Board> list = new ArrayList<Board>();
		try {
			service.writeBoard(board);
			Criteria cri = new Criteria();
			list = service.selectBoardList(cri);
			return new ResponseEntity<List<Board>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Board>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	//글 수정하기 + 첨부파일 수정(예정)
	@PutMapping(value = "/{spaceid}/detail")
	public ResponseEntity<Board> modifyBoard(@RequestBody Board board) {
		Board board2 = new Board();
		try {
			service.modifyBoard(board);
			Criteria cri = new Criteria();
			board2 = service.selectBoardByid(board.getBoardid());
			return new ResponseEntity<Board>(board2,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Board>(board2,HttpStatus.BAD_REQUEST);
		}
	}
	
	//글 삭제하기
	@DeleteMapping(value = "/{spaceid}/detail")
	public ResponseEntity<List<Board>> deleteBoard(@PathVariable("spaceid") int spaceid, @RequestBody Board board) {
		List<Board> list = new ArrayList<Board>();
		try {
			service.deleteBoard(board.getBoardid());
			Criteria cri = new Criteria();
			list = service.selectBoardList(cri);
			return new ResponseEntity<List<Board>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Board>>(list,HttpStatus.BAD_REQUEST);
		}
	}
}
