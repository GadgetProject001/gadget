package kr.or.gadget.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.gadget.dto.Chat;
import kr.or.gadget.service.ChatService;

@RequestMapping("/chat/")
@RestController
public class ajaxController {
	
	@Autowired
	private ChatService chatservice;
	
	@PostMapping("premessage")
	public List<Chat> chatList (@RequestParam ("workspace") int spaceid){
		
		
		return null;
	}
	
}
