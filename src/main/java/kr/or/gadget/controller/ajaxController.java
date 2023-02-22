package kr.or.gadget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.gadget.service.ChatService;

@RequestMapping("/chat/")
@RestController
public class ajaxController {
	
	@Autowired
	private ChatService chatservice;
	
	
}
