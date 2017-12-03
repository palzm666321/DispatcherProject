package cn.mldn.fjn.util;

import java.lang.reflect.Method;

public class MethodUtil {
	private MethodUtil() {}
	
	public static Method getMethod(Class<?> cls,String str) {
		Method[] method=cls.getMethods();
		for(int i=0;i<method.length;i++) {
			if(str.equals(method[i].getName())) {
				return method[i];
			}
		}
		return null;
	}
}
