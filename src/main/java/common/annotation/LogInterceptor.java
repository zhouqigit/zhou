package common.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import common.annotation.LogConfig;;

public class LogInterceptor extends HandlerInterceptorAdapter {

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerMethod method = (HandlerMethod)handler;
		LogConfig logConfig = method.getMethod().getAnnotation(LogConfig.class);
		String operation = logConfig.operation();
		System.out.println(operation+"11111");
	}
}
