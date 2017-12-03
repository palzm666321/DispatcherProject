package cn.mldn.fjn.util.web;

import java.text.SimpleDateFormat;

public class DataConverterUtil {
	
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
