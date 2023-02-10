package kr.or.gadget.dao;

import java.util.List;

import kr.or.gadget.dto.Attach;
import kr.or.gadget.dto.Board;
import kr.or.gadget.dto.Criteria;

public interface BoardDao {
	//특정항목의 글 목록 출력
	List<Board> selectBoardList(Criteria cri);
	//글 자세히 보기
	Board selectBoardByid(int boardid);
	//첨부파일 리스트 가져오기
	List<Attach> getAttachListbyid(int boardid);
	//글 작성하기
	void writeBoard(Board board);
	//첨부파일 삽입하기
	void insertAttach(Attach attach);
	//글 갱신하기
	boolean modifyBoard(Board board);
	//첨부파일 전부 삭제하기
	void deleteAttach(int boardid);
	//글 삭제하기
	int deleteBoard(int boardid);
}
