package kr.or.gadget.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TodoContent {
  private int contentid;
  private String title;
  private String content;
  private String wdate;
  private String startdate;
  private String enddate;
  private int stateid;
}
