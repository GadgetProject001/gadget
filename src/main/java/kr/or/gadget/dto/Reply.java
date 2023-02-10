package kr.or.gadget.dto;

import lombok.Data;

@Data
public class Reply {
	private int replyid;
	private String content;
	private String writer;
	private String wdate;
	private int boardid;
	private String userid;
}
