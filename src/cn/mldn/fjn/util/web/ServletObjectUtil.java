package cn.mldn.fjn.util.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletObjectUtil {
	private static ServletConfig config;
	private static ServletContext context;
	private static ThreadLocal<HttpServletRequest> THREAD_REQUEST=new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> THREAD_RESPONSE=new ThreadLocal<HttpServletResponse>();
	public static ServletConfig getConfig() {
		return config;
	}
	public static void setConfig(ServletConfig config) {
		ServletObjectUtil.config = config;
	}
	public static ServletContext getContext() {
		return context;
	}
	public static void setContext(ServletContext context) {
		ServletObjectUtil.context = context;
	}
	public static HttpServletRequest getTHREAD_REQUEST() {
		return THREAD_REQUEST.get();
	}
	public static void setTHREAD_REQUEST(HttpServletRequest request) {
		THREAD_REQUEST.set(request);
	}
	public static HttpServletResponse getTHREAD_RESPONSE() {
		return THREAD_RESPONSE.get();
	}
	public static void setTHREAD_RESPONSE(HttpServletResponse response) {
		THREAD_RESPONSE.set(response);
	}
	public static void close() {
		THREAD_REQUEST.remove();
		THREAD_RESPONSE.remove();
	}
	
}
