package common;



import common.annotation.LogConfig;
import controller.UserController;

public class TestCommon {

	public static void fun() throws Exception, Exception{
		LogConfig logConfig=UserController.class.getMethod("toLogin", null).getAnnotation(LogConfig.class);
//		LogConfig logConfig=field.getAnnotation(LogConfig.class);
		System.out.println(logConfig.operation());
	}
}
