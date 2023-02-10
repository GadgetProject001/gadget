package kr.or.gadget.dto;

import java.util.List;

import lombok.Data;

@Data
public class Board {

	private int boardid;
	private String title;
	private String content;
	private String writer;
	private String wdate;
	private String udate;
	private int repnum;
	private int bcodeid;
	private int spaceid;
	private String userid;
	private List<Attach> attachList;
}
