package cn.mldn.fjn.util;

public class StringUtils {
	/**
	 * 进行首字母的大写转换
	 * @param str 要转换的字符串
	 * @return 转换后的字符串
	 */
	public static String initcap(String str) {
		if(str==null||"".equals(str)) {
			return null;
		}
		if(str.length()==1) {
			return str.toUpperCase();
		}
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
}
