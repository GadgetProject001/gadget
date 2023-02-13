package kr.or.gadget.dto;

import lombok.Data;

@Data
public class Users {
	
	public Users() {}

	public Users(String userid, String imageUrl, String username, String logintype, String email) {
		this.userid = userid;
		this.imgurl = imageUrl;
		this.username = username;
		this.logintype = logintype;
		this.email = email;
	}
	
	private String userid;
    private String pwd;
	private String imgurl;
	private String username;
	private String regdate;
	private String logintype;
	private String lastdate;
	private String callnum;
	private String email;
	private int enabled;
	private String token; /* 임시 컬럼 */
}	
