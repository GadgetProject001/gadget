package kr.or.gadget.dto;

import lombok.Data;

@Data
public class Socialuser {
	private String userid;
	private String socialauth;
	private String externaleid;
	private String socialtype;
}
