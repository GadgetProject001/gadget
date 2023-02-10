package kr.or.gadget.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.gadget.dao.BoardDao;
import kr.or.gadget.dto.Attach;
import kr.or.gadget.dto.Board;
import kr.or.gadget.dto.Criteria;
import lombok.Setter;

@Service
public class BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlsession;
	
	public List<Board> selectBoardList(Criteria cri){
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		List<Board> list = boarddao.selectBoardList(cri);
		return list;
	}
	
	public Board selectBoardByid(int boardid) {		
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		List<Attach> attachlist = boarddao.getAttachListbyid(boardid);
		Board board = boarddao.selectBoardByid(boardid);
		board.setAttachList(attachlist);
		return board;
	}
	
	public void writeBoard(Board board) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		
		boarddao.writeBoard(board);
		if (board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		
//		board.getAttachList().forEach(attach -> {
//
//			attach.setBoardid(board.getBoardid());
//			boarddao.insertAttach(attach);
//		});
	}
	
	public boolean modifyBoard(Board board) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		boolean modifyResult = boarddao.modifyBoard(board);

//		boarddao.deleteAll(board.getBno());
//		if (modifyResult && board.getAttachList() != null) {
//
//			board.getAttachList().forEach(attach -> {
//
//				attach.setBoardid(board.getBoardid());
//				boarddao.insertAttach(attach);
//			});
//		}
		return modifyResult;
	}
	
	public int deleteBoard(int boardid) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		//boarddao.deleteAttach(boardid);
		return boarddao.deleteBoard(boardid);
	}
}