package cn.mldn.fjn.util.action;

import java.text.MessageFormat;

import cn.mldn.fjn.util.ResourceUtil;

public class ActionResourceUtil {
	private static final ResourceUtil PAGE_RESOURCE=new ResourceUtil("cn.mldn.fjn.resource.page");
	private static final ResourceUtil MESSAGE_RESOURCE=new ResourceUtil("cn.mldn.fjn.resource.message");
	
	/**
	 * 读取资源提示信息
	 * @param key 要读取的资源key
	 * @param ages 占位符的设置内容
	 * @return 返回文字的提示信息
	 */
	public static String getMessage(String key,String ... ages) {
		String val=null;
		try{
			val=MessageFormat.format(MESSAGE_RESOURCE.get(key), ages);
		}catch(Exception e) {}
		return val;
	}
	
	public static String getPage(String key){
		String val=null;
		try{
			val=PAGE_RESOURCE.get(key);
		}catch(Exception e) {}
		return val;
	}
}
