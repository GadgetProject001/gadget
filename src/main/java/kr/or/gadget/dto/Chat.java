package kr.or.gadget.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
	private int chatid;
	private String wdate;
	private String content;
	private int chatidx;
	private String userid;
	private String username;
	private String spaceid;
}
