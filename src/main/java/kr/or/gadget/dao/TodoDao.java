package kr.or.gadget.dao;

import java.util.List;

import kr.or.gadget.dto.State;
import kr.or.gadget.dto.TodoContent;
import kr.or.gadget.dto.TodoContentJoinState;
import kr.or.gadget.dto.TodoContentJoinStateJoinUsers;

public interface TodoDao {
  public List<TodoContent> selectTodoList (int spaceid);
  public int updateTodoState (TodoContentJoinState todoContentJoinState);
  public int updateStateIndex(State state);
  public TodoContentJoinStateJoinUsers selectTodoContent(int contentid);
  public int insertTodoContent(TodoContentJoinStateJoinUsers TodoContentJoinStateJoinUsers);
  public int updateTodoContent(TodoContentJoinState todoContentJoinState);
//  public State selectTodoOrder(int spaceid);
  public String selectTodoOrder(int spaceid);
}