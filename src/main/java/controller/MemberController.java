package controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import common.param.WxParam;
import entity.Member;
import service.MemberService;
import util.HttpUtil;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(Member member,@RequestParam("code") String code,@RequestParam("phone") String phone){
		String url="https://api.weixin.qq.com/sns/jscode2session";//appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
		try {
			Map<String, String> dataMap=new HashMap<String, String>();
			dataMap.put("appid", WxParam.getAppid());
			dataMap.put("secret", WxParam.getSecret());
			dataMap.put("js_code", code);
			dataMap.put("grant_type", "authorization_code");
			System.out.println(code);
			System.out.println(WxParam.getAppid());
			System.out.println(WxParam.getSecret());
			String result=HttpUtil.strClient(HttpUtil.urlDataMapToStr(dataMap, url), "Get");
			Map<String, Object> map=JSON.parseObject(result);
			member.setOpenid(map.get("openid").toString());
			member.setPhone(phone);
			memberService.save(member);
			return "新增用户成功";
		} catch (Exception e) {
			return "新增用户失败";
		}
	}
}
