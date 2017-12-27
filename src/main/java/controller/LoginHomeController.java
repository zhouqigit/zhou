package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginHomeController {

	@RequestMapping("/login")
	public String login() throws Exception{
		System.out.println("进入登录界面");
		return "login";
	}
}
