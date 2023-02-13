package kr.or.gadget.dao;

import java.util.List;

import kr.or.gadget.dto.State;
import kr.or.gadget.dto.TodoContent;
import kr.or.gadget.dto.TodoContentJoinState;

public interface TodoDao {
  public List<TodoContent> selectTodoList (int spaceid);
  public int updateTodoState (TodoContentJoinState todoContentJoinState);
  public int updateStateIndex(State state);
  public TodoContentJoinState selectTodoContent(int contentid);
  public int insertTodoContent(TodoContentJoinState todoContentJoinState);
  public int updateTodoContent(TodoContentJoinState todoContentJoinState);
}