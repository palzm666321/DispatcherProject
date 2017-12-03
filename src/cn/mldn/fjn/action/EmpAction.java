package cn.mldn.fjn.action;

import java.util.Date;

import cn.mldn.fjn.util.web.ModelAndView;
import cn.mldn.fjn.util.web.ServletObjectUtil;

public class EmpAction {
	
	/**
	 * 实现雇员数据追加前的控制方法
	 * @return 设置跳转的处理路径
	 */
	public void add(String mid,long empno,String ename,double sal,Date hiredate,int age) {//做一个页面的跳转处理
		System.out.println("雇员信息：mid="+mid+"，empno="+empno+"，ename="+ename+"，sal="+sal+"，hiredate="+hiredate+"，age="+age);
	}
	
	public String addPre() {
		return "/pages/back/admin/emp/emp_add.jsp";
	}
	
	
	public void print() {
		try {
			ServletObjectUtil.getTHREAD_RESPONSE().getWriter().println("<h1>world，世界！</h1>");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ModelAndView editPre() {//做一个增加前的跳转页面
		//将跳转的页面信息设置到ModelAndView中
		ModelAndView mav=new ModelAndView("/pages/back/admin/emp/emp_edit.jsp");
		mav.addObject("msg", "www.mldn.com");//设置内容，避免了request.setAttribute
		return mav;
	}
}
