package cn.mldn.fjn.util.web;

import cn.mldn.fjn.util.action.ActionResourceUtil;
/**
 * 进行分页的参数处理操作
 * @author lenovo
 *
 */
public class SplitPageUtil {
	private long currentPage;
	private int lineSize=5;
	private String column;
	private String keyWord;
	/**
	 * 将你需要进行模糊插叙的columnData（下拉框）传递到组件之中，目的是为了属性操作
	 * @param columnData 分页搜索的下拉列表数据
	 * @param handleUrlKey 设置分页路径的key
	 */
	public SplitPageUtil(String columnData,String handleUrlKey) {
		ServletObjectUtil.getTHREAD_REQUEST().setAttribute("columnData", columnData);
		ServletObjectUtil.getTHREAD_REQUEST().setAttribute("handleUrl", ActionResourceUtil.getPage(handleUrlKey));
		try {//这行代码出错只有不传递或传递非法参数的时候出现
			this.currentPage=Long.parseLong(ServletObjectUtil.getTHREAD_PARAMETER().getParameter("cp"));
		}catch(Exception e) {}
		try {//如果出错就使用默认值
			this.lineSize=Integer.parseInt(ServletObjectUtil.getTHREAD_PARAMETER().getParameter("ls"));
		}catch(Exception e) {}
		this.column=ServletObjectUtil.getTHREAD_PARAMETER().getParameter("col");
		this.keyWord=ServletObjectUtil.getTHREAD_PARAMETER().getParameter("kw");
		if(this.column==null) {
			this.column="";
		}
		if(this.keyWord==null) {
			this.keyWord="";
		}
		ServletObjectUtil.getTHREAD_REQUEST().setAttribute("currentPage", this.currentPage);
		ServletObjectUtil.getTHREAD_REQUEST().setAttribute("lineSize", this.lineSize);
		ServletObjectUtil.getTHREAD_REQUEST().setAttribute("keyWord", this.keyWord);
		ServletObjectUtil.getTHREAD_REQUEST().setAttribute("column", this.column);
		
	}
	public long getCurrentPage() {
		return currentPage;
	}
	public int getLineSize() {
		return lineSize;
	}
	public String getColumn() {
		return column;
	}
	public String getKeyWord() {
		return keyWord;
	}
}
