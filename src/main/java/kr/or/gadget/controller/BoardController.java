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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.gadget.dto.Board;
import kr.or.gadget.dto.Criteria;
import kr.or.gadget.dto.Reply;
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
			System.out.println(list.toString());
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
			System.out.println(board);
			service.modifyBoard(board);
			Criteria cri = new Criteria();
			System.out.println(cri);
			board2 = service.selectBoardByid(board.getBoardid());
			System.out.println(board2);
			return new ResponseEntity<Board>(board2,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Board>(board2,HttpStatus.BAD_REQUEST);
		}
	}
	
	//글 삭제하기
	@DeleteMapping(value = "/{boardid}")
	public ResponseEntity<List<Board>> deleteBoard(@PathVariable("boardid") int boardid) {
		List<Board> list = new ArrayList<Board>();
		try {
			service.deleteBoard(boardid);
			Criteria cri = new Criteria();
			list = service.selectBoardList(cri);
			return new ResponseEntity<List<Board>>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Board>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	//글 수 총합
	@GetMapping(value = "/count")
	public int getTotalCount(Criteria cri) {
		return service.getTotalCount(cri);
	}
	//댓글 수 총합
	@GetMapping(value = "/reply/count")
	public int getTotCountReply(Criteria cri) {
		return service.getTotCountReply(cri);
	}
	
	@GetMapping(value = "/reply/{boardid}")
	public ResponseEntity<List<Reply>> selectReplyByBoardid(@PathVariable("boardid") int boardid) {
		List<Reply> list = new ArrayList<Reply>();
		try {
			Criteria cri = new Criteria();
			cri.setBoardid(boardid);
			list = service.selectReplyByBoardid(cri);
			return new ResponseEntity<List<Reply>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Reply>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/reply")
	public ResponseEntity<List<Reply>> writeReply(@RequestBody Reply reply) {
		List<Reply> list = new ArrayList<Reply>();
		try {
			service.writeReply(reply);
			Criteria cri = new Criteria();
			list = service.selectReplyByBoardid(cri);
			return new ResponseEntity<List<Reply>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Reply>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PatchMapping(value = "/reply")
	public ResponseEntity<List<Reply>> modifyReply(@RequestBody Reply reply) {
		List<Reply> list = new ArrayList<Reply>();
		try {
			service.modifyReply(reply);
			Criteria cri = new Criteria();
			list = service.selectReplyByBoardid(cri);
			return new ResponseEntity<List<Reply>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Reply>>(list,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value = "/reply/{replyid}")
	public ResponseEntity<List<Reply>> deleteReply(@PathVariable("replyid") int replyid) {
		List<Reply> list = new ArrayList<Reply>();
		try {
			service.deleteReply(replyid);
			Criteria cri = new Criteria();
			list = service.selectReplyByBoardid(cri);
			return new ResponseEntity<List<Reply>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Reply>>(list,HttpStatus.BAD_REQUEST);
		}
	}
}
