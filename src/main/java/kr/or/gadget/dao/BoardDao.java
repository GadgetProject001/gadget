package kr.or.gadget.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.gadget.dto.Attach;
import kr.or.gadget.dto.Board;
import kr.or.gadget.dto.Criteria;
import kr.or.gadget.dto.Reply;

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
	int modifyBoard(Board board);
	//첨부파일 전부 삭제하기
	void deleteAttach(int boardid);
	//글 삭제하기
	int deleteBoard(int boardid);
	//글 총 갯수 가져오기
	int getTotalCount(Criteria cri);
	//댓글 작성하기
	void writeReply(Reply reply);
	//댓글 수정하기
	int modifyReply(Reply reply);
	//댓글 삭제하기
	int deleteReply(int replyid);
	//댓글 수 갱신
	void updateReplyCnt(@Param("boardid") int boardid);
	//댓글 가져오기
	List<Reply> selectReplyByBoardid(Criteria cri);
	//댓글 총 갯수 가져오기
	int getTotCountReply(Criteria cri);
}
