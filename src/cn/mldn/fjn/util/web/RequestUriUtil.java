package cn.mldn.fjn.util.web;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义一个专门的程序类，用来拆分路径，返回Action的路径以及方法名称！
 * @author lenovo
 *
 */
public class RequestUriUtil {
	private RequestUriUtil() {}
	
	/**
	 * 进行给定路径的拆分处理操作，路径的组成，XxxAction方法！方法名称.action
	 * @param request 请求对象
	 * @return 返回的信息里面包含有两个参数
	 * 第一个参数:为Action的访问路径
	 * 第二个参数:为Action类中的调用方法
	 */
	public static String[] splitUri(HttpServletRequest request) {
		String[] temp=new String[2];
		String uri=request.getRequestURI().replaceFirst(request.getContextPath(), "");
		String result[]=uri.split("!");
		temp[0]=result[0];
		temp[1]=result[1].substring(0,result[1].lastIndexOf("."));
		return temp;
	}
}
