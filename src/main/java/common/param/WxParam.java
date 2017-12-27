package common.param;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WxParam {

	private static Properties props = new Properties();
	
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("wx.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getAppid(){
		return props.getProperty("appid");
	}
	public static String getSecret(){
		return props.getProperty("secret");
	}
	
}
