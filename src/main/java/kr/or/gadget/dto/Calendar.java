package kr.or.gadget.dto;

import lombok.Data;

@Data
public class Calendar {
	private int id;
	private String groupId;
	private String title;
	private String writer;
	private String userid;
	private String content;
	private String start;
	private String end;
	private boolean allday;
	private String textColor;
	private String backgroundColor;
	private String borderColor;
	private int spaceid;
}
