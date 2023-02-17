package kr.or.gadget.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Users {
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
