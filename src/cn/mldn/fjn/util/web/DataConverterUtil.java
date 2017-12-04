package cn.mldn.fjn.util.web;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import cn.mldn.fjn.util.StringUtils;

public class DataConverterUtil {
	
	/**
	 * 进行vo的实例化处理操作，同时设置请求参数
	 * @param voClass 要执行的vo类型
	 * @return 实例化的vo类
	 */
	public static Object converterVO(Class<?> voClass) {
		Object obj=null;
		try {
			obj=voClass.newInstance();
			Field[] fields=voClass.getDeclaredFields();
			for(int i=0;i<fields.length;i++) {
				String paramName=fields[i].getName();
				Method method=voClass.getMethod("set"+StringUtils.initcap(paramName),fields[i].getType());
				method.invoke(obj,converter(ServletObjectUtil.getTHREAD_PARAMETER().getParameter(paramName),fields[i].getType().getName()));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * 将字符串数组进行指定格式的转换
	 * @param value 字符串数组
	 * @param type 转换类型
	 * @return 转换后的数组参数
	 */
	public static Object converterArray(String value[],String type) {
		Object result=value;
		if("long[]".equals(type)||"java.lang.Long[]".equals(type)) {
			long arr[]=new long[value.length];
			for(int i=0;i<value.length;i++) {
				arr[i]=Long.parseLong(value[i]);
			}
			result=arr;
		}
		return result;
	}
	
	
	/**
	 * 实现字符串与具体类型之间的转换处理操作
	 * @param value 表示接受的字符串参数
	 * @param type 表示转换目标类型
	 * @return 转换后的数据结果
	 */
	public static Object converter(String value,String type) {
		Object obj=value;
		if("int".equals(type)||"java.lang.Integer".equals(type)){
			obj=Integer.parseInt(value);
		}
		if("double".equals(type)||"java.lang.Double".equals(type)) {
			obj=Double.parseDouble(value);
		}
		if("long".equals(type)||"java.lang.Long".equals(type)) {
			obj=Long.parseLong(value);
		}
		if("java.util.Date".equals(type)) {
			SimpleDateFormat sdf=null;
			if(value.matches("\\d{4}-\\d{2}-\\d{2}")) {
				sdf=new SimpleDateFormat("yyyy-MM-dd");
			}
			if(value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
				sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			if(sdf!=null) {
				try {
					obj=sdf.parse(value);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}
}
