package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.TestCommon;
import common.annotation.LogConfig;


@Controller
@RequestMapping("/user")
public class UserController {

	@LogConfig(operation="注释进入登录界面11222")
	@RequestMapping("/login")
	public String toLogin() throws Exception{
		System.out.println("进入登录界面");
		//TestCommon.fun();
		return "login";
	}
	
	//@LogConfig
	@RequestMapping("/login1")
	public String toLogin1() throws Exception{
		System.out.println("进入登录界面");
		TestCommon.fun();
		return "login";
	}
}
