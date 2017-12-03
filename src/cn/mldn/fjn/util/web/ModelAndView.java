package cn.mldn.fjn.util.web;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 该类主要功能将控制层之中的属性设置到request范围之中，而后设置跳转路径
 * @author lenovo
 *
 */
public class ModelAndView {
	private String uri;//跳转路径
	public ModelAndView() {}
	public ModelAndView(String uri) {
		this.uri=uri;
	}
	public void addObjectMap(Map<String,Object> actionMap) {
		if(actionMap.size()==0||actionMap==null) {
			return ;
		}
		Iterator<Entry<String, Object>> it=actionMap.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String,Object> entry=it.next();
			ServletObjectUtil.getTHREAD_REQUEST().setAttribute(entry.getKey(), entry.getValue());
		}
	}
	/**
	 * 设置要保存到JSP视图中的属性名称和属性内容，保存范围默认就是request
	 * @param name
	 * @param value
	 */
	public void addObject(String name,Object value) {
		ServletObjectUtil.getTHREAD_REQUEST().setAttribute(name, value);
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
