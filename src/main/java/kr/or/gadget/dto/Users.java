package kr.or.gadget.dto;

import lombok.Data;

@Data
public class Users {

	private String userid;
	private String pwd;
	private String imgurl;
	private String username;
	private String regdate;
	private String lastdate;
	private String callnum;
	private String email;
	private int enabled;
}	
