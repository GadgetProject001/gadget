package kr.or.gadget.dto;

import lombok.Data;

@Data
public class TodoContentJoinStateJoinUsers {
	private int stateid;
	private String statename;
	private String stateindex;
	private int spaceid;
	private int contentid;
    private String title;
	private String content;
	private String wdate;
	private String startdate;
	private String enddate;
	private String userid;
	private String imgurl;
	private String username;
}
