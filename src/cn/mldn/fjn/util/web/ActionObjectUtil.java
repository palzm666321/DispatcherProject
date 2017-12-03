package cn.mldn.fjn.util.web;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.mldn.fjn.util.MethodUtil;
import cn.mldn.fjn.util.web.exception.DispatcherException;
import cn.mldn.fjn.util.web.exception.action.SplitUrlException;

/**
 * 这个类负责具体的Action调用处理操作，这个类最终会反射调用Action中的给定处理方法
 * @author lenovo
 *
 */
public class ActionObjectUtil {
	private Map<String,Class<?>> actionMap;
	private String[] temp;
	private HttpServletRequest request;
	/**
	 * 实例化Action对象的处理类对象
	 * @param actionMap 包含了所有要启动时加载的Class类对象
	 * @param temp 对路径拆分后的结果，一个包含Action路径，另一个包含具体方法
	 * @param request  用户的请求对象
	 * @throws DispatcherException 如果给出的路径有问题，则抛出异常
	 */
	public ActionObjectUtil(Map<String,Class<?>> actionMap,String[] temp,HttpServletRequest request) throws DispatcherException{
		if(temp==null||temp.length!=2) {
			throw new SplitUrlException("请求路径错误或者长度不等于2");
		}
		this.actionMap=actionMap;
		this.temp=temp;
		this.request=request;
	}

	public String handleAcation() throws Exception{//处理Action的反射调用
		//根据拆分的路径信息获取请求的反射Action对象
		Object obj=this.actionMap.get(temp[0]).newInstance();
		Method method=MethodUtil.getMethod(this.actionMap.get(temp[0]),temp[1]);
		if(method.getParameterTypes().length==0) {//方法上没有定义参数
			if("void".equals(method.getParameterTypes().toString())) {//方法返回类型是否为“void”
				method.invoke(obj);
				return "nopath";
			}else {
				Object resultobj=method.invoke(obj);
				if(resultobj!=null) {
					if(resultobj instanceof java.lang.String) { //返回的类型是字符串
						return resultobj.toString();
					}
					if(resultobj instanceof ModelAndView) {
						ModelAndView mav=(ModelAndView)resultobj;
						return mav.getUri();
					}
				}
			}
		}
		return null;
	}
}
