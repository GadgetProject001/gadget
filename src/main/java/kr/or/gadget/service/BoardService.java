package kr.or.gadget.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.gadget.dao.BoardDao;
import kr.or.gadget.dto.Attach;
import kr.or.gadget.dto.Bcode;
import kr.or.gadget.dto.Board;
import kr.or.gadget.dto.Criteria;
import kr.or.gadget.dto.Reply;
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
		boolean modifyResult = false;
		try {
			modifyResult = (boarddao.modifyBoard(board)==1);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
	
	public int getTotalCount(Criteria cri) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		return boarddao.getTotalCount(cri);
	};
	
	public int getTotCountReply(Criteria cri) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		return boarddao.getTotCountReply(cri);
	}
	
	public void updateReplyCnt(@Param("boardid") int boardid) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		boarddao.updateReplyCnt(boardid);
	}
	
	public List<Reply> selectReplyByBoardid(Criteria cri) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		List<Reply> list = boarddao.selectReplyByBoardid(cri);
		return list;
	}
	
	public void writeReply(Reply reply) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		boarddao.writeReply(reply);
		boarddao.updateReplyCnt(reply.getBoardid());
	}
	
	public int modifyReply(Reply reply) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		return boarddao.modifyReply(reply);
	}
	
	public int deleteReply(int replyid) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		int num = boarddao.deleteReply(replyid); 
		boarddao.updateReplyCnt(replyid);
		return num;
	}
	
	public int insertBcode(Bcode bcode) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		int num = 0;
		try {
			num = boarddao.insertBcode(bcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("프로시져 호출완료");
		return num;
	}
	
	public int deleteBcode(int bcodeid) {
		BoardDao boarddao = sqlsession.getMapper(BoardDao.class);
		int result = 0;
		try {
			result += boarddao.deleteBcode(bcodeid);
			result += boarddao.deleteBcodeFromSide(bcodeid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
