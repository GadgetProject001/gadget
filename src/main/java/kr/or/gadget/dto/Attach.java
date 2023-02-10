package kr.or.gadget.dto;

import lombok.Data;

@Data
public class Attach {
	private String uuid;
	private String uploadpath;
	private String filename;
	private String filetype;
	private int boardid;
}
