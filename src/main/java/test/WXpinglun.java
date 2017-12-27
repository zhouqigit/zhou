package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import util.HttpForm;
import util.HttpUtil;

public class WXpinglun {
	
	
	private static final String appID="wx1930d6c04032078c";
	private static final String appsecret="360305a7efd93023a6461bf9eb7ecc63";
//	private static final String appID="wx77d87693cb1ed3c4";
//	private static final String appsecret="054b315598e71e04c9287b2fdbba83c4";
	private static String accessToken="aPqZEcTj1kbzo-Y7ngMxxcQhne1KKighgvjinTAI_T_p89tf-3rGDPTQ-N8d6DtSfuDZ0orsBGXnEoHgRp9FT4r9EJrfBOaXyKzoE76_1edbINjqYJMMLkl8XjQr-ys0YILkCDABLO";
	
	/**
	 * 获取accessToken
	 */
	@Test
	public void getAccessToken(){
		String str=HttpUtil.strClient("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appID+"&secret="+appsecret+"", null);
		System.out.println(str);
	}
	
	/**
	 * 新增其他类型永久素材
	 */
	@Test
	public void uploaImg(){
		String url="https://api.weixin.qq.com/cgi-bin/material/add_material";
//		String url="https://api.weixin.qq.com/cgi-bin/media/uploadimg";
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("media", "C:/Users/THINK/Desktop/细川项目10-19/细川项目0614/images/register-header-bg.jpg");
		Map<String, String> textMap = new HashMap<String, String>();
		textMap.put("access_token", accessToken);
		textMap.put("type", "thumb");
//		textMap.put("", "");
//		textMap.put("", "");
		String contentType = "";//image/png
		System.out.println(HttpForm.formClient(url, textMap, fileMap,contentType));
	}
	
	/**
	 * 新增永久素材
	 * @throws Exception
	 */
	@Test
	public void add_news() throws Exception{
		List<Map<String, String>> lisDataMap=new ArrayList<Map<String, String>>();
		Map<String, String> dataMap=new HashMap<String, String>();
		dataMap.put("title", "测试标题");
		dataMap.put("thumb_media_id", "c9q5vPbUN4SRn1lKaXK97c_qbA11Jm8weUzQQHb72gc");
		dataMap.put("authod", "zhou");
		dataMap.put("digest", "图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空");
		dataMap.put("show_cover_pic", "1");
		dataMap.put("content", "图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS");
		dataMap.put("content_source_url", "https://www.baidu.com/");
		dataMap.put("need_open_comment", "1");
		dataMap.put("only_fans_can_comment", "0");
		lisDataMap.add(dataMap);
		StringBuffer sbu=new StringBuffer();
		sbu.append("{\"articles\":");
		sbu.append(JSON.toJSONString(lisDataMap));
		sbu.append("}");
		
		String url="https://api.weixin.qq.com/cgi-bin/material/add_news?access_token="+accessToken;
		String res=HttpUtil.jsonClient(url, sbu.toString());
		
		System.out.println(res);
	}
	/**
	 * 上传图文消息素材
	 * @throws Exception
	 */
	@Test
	public void uploadnews() throws Exception{
		List<Map<String, String>> lisDataMap=new ArrayList<Map<String, String>>();
		Map<String, String> dataMap=new HashMap<String, String>();
		dataMap.put("thumb_media_id", "c9q5vPbUN4SRn1lKaXK97ROsbSSGW29IwFc7nsPZq98");
		dataMap.put("author", "zhou");
		dataMap.put("title", "测试标题");
		dataMap.put("content_source_url", "https://www.baidu.com/");
		dataMap.put("content", "图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS");
		dataMap.put("digest", "图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空");
		dataMap.put("show_cover_pic", "1");
		//dataMap.put("need_open_comment", "1");
		//dataMap.put("only_fans_can_comment", "0");
		lisDataMap.add(dataMap);
		StringBuffer sbu=new StringBuffer();
		sbu.append("{\"articles\":");
		sbu.append(JSON.toJSONString(lisDataMap));
		sbu.append("}");
		System.out.println(sbu.toString());
		String url="https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="+accessToken;
		String res=HttpUtil.jsonClient(url, sbu.toString());
		
		System.out.println(res);
	}
	
	@Test
	public void sendMessage() throws Exception{
//		String url="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="+accessToken;
		String url="https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+accessToken;
		StringBuffer sbu=new StringBuffer();
		sbu.append("{\"filter\":{");
		sbu.append("\"is_to_all\":\"true\",");
		//sbu.append("\"tag_id\":\"2\"");
		sbu.append("\"image\":{");
		sbu.append("\"media_id\":\"YCwrnYUqNjx4u2Jl_5HfLWrreuQbnFJsqK1KlCwzZJQ\"");
//		sbu.append("\"media_id\":\"YCwrnYUqNjx4u2Jl_5HfLTV8wXG1n8bLOQGcGVb94Hc\"");
		sbu.append("},");
		sbu.append("\"msgtype\":\"image\",");
		sbu.append("\"send_ignore_reprint\":\"1\"");
		sbu.append("}");
		sbu.append("}");
		String jsonstr=sbu.toString();//"{\"touser\":[\"oAhBss23b5IaMf-D8ljqdxkLSM40\",\"oAhBss6Imvijmyb-gTikYMYZTWDQ\"],\"msgtype\":\"text\",\"text\":{\"content\":\"hello from boxer.\"}}";
		String res=HttpUtil.jsonClient(url, jsonstr);
		
		System.out.println(res);
	}
	
	public static void main(String[] args) throws Exception {
		String url="https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+accessToken;
//		String url="https://api.weixin.qq.com/cgi-bin/material/get_material?access_token="+accessToken;
		StringBuffer sbu=new StringBuffer();
		sbu.append("{");
//		sbu.append("\"media_id\":\"c9q5vPbUN4SRn1lKaXK97XLZ7823pbqtbNeGs4cM4sI\",");
		sbu.append("\"type\":\"thumb\",");
		sbu.append("\"offset\":\"0\",");
		sbu.append("\"count\":\"20\"");
		sbu.append("}");
		String res=HttpUtil.jsonClient(url, sbu.toString());
		
		System.out.println(res);
	}
	
	public static void main1(String[] args) {
		Long a=new Date().getTime();
		Long b=(long) 1510107723;
		System.out.println(a);
		System.out.println((a-(b*1000))/(3600));
	}
}

/*
新增永久素材图片
{"media_id":"YCwrnYUqNjx4u2Jl_5HfLbelKeiduc8ZIJvwL2lTAJo",
"url":"http://mmbiz.qpic.cn/mmbiz_jpg/TNnw5vL0eo3KMYlUDdZZMnCyXqf9s8ffKTmkvwBHiawNZc57QaBWPBR2Nm3hpQ4Q0mXAdNPYFcske2GH7bicAIpA/0?wx_fmt=jpeg"}
{"media_id":"YCwrnYUqNjx4u2Jl_5HfLXz-AV92RoZrEHCMrV5HVW4",
"url":"http://mmbiz.qpic.cn/mmbiz_jpg/TNnw5vL0eo3KMYlUDdZZMnCyXqf9s8ffKTmkvwBHiawNZc57QaBWPBR2Nm3hpQ4Q0mXAdNPYFcske2GH7bicAIpA/0?wx_fmt=jpeg"}
{"media_id":"YCwrnYUqNjx4u2Jl_5HfLWrreuQbnFJsqK1KlCwzZJQ",
"url":"http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/TNnw5vL0eo3KMYlUDdZZMnCyXqf9s8ffKTmkvwBHiawNZc57QaBWPBR2Nm3hpQ4Q0mXAdNPYFcske2GH7bicAIpA\/0?wx_fmt=jpeg"}

新增永久素材
{"media_id":"YCwrnYUqNjx4u2Jl_5HfLVYH6nSuHLtpwEqQU8sD8zk"}
{"media_id":"YCwrnYUqNjx4u2Jl_5HfLY22G0J2fYBPOh2k8jf_b3Y"}
{"media_id":"YCwrnYUqNjx4u2Jl_5HfLTV8wXG1n8bLOQGcGVb94Hc"}



http://mp.weixin.qq.com/s?__biz=MzAxMDIwMzY1MA==&mid=100000006&idx=1&sn=424bd2899b7add94d614e0913df32ff6&chksm=1b52a66b2c252f7d418deadec09595e7cd27aa557eba8184d3ca58d74326cb696583cd3f9ac2#rd










*/