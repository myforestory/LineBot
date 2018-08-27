package tw.idv.aloha.lineBot.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineBotRSController {
	@RequestMapping(value="/callback")
    public void callback(@RequestBody String message) {  
		System.out.println(message);
	}
}
