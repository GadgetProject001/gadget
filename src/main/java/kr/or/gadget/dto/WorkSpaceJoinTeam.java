package kr.or.gadget.dto;

import lombok.Data;

@Data
public class WorkSpaceJoinTeam {
  private int spaceid;
  private String spacename;
  private int teamid;
  private String userid;
  private int count;
}
