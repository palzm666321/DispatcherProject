package cn.mldn.fjn.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.fjn.util.ResourceUtil;
import cn.mldn.fjn.util.action.ActionResourceUtil;
import cn.mldn.fjn.util.web.ActionObjectUtil;
import cn.mldn.fjn.util.web.ParameterUtil;
import cn.mldn.fjn.util.web.ParameterValidatorUtil;
import cn.mldn.fjn.util.web.RequestUriUtil;
import cn.mldn.fjn.util.web.ServletObjectUtil;

@SuppressWarnings("serial")
@WebServlet(loadOnStartup=0,urlPatterns={"*.action"},initParams= {@WebInitParam(name="actionBaseName",value="cn.mldn.fjn.resource.action")})
@WebFilter(urlPatterns= {"/pages/back/admin/emp/*"})
public class DispatcherServlet extends HttpServlet implements Filter{
	private static Map<String,Class<?>> actionMap=new HashMap<String,Class<?>>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletObjectUtil.setConfig(config);
		ServletObjectUtil.setContext(config.getServletContext());
		String baseName=config.getInitParameter("actionBaseName");
		String basePath=config.getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"classes"+File.separator+baseName.replaceAll("\\.","/")+".properties";
		Properties pro=new Properties();
		try {
			pro.load(new FileInputStream(basePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Iterator<Entry<Object, Object>> it = pro.entrySet().iterator();
		if(pro.size()>0) {
			try{
				while(it.hasNext()) {
					Entry<Object,Object> entry=it.next();
					actionMap.put(entry.getKey().toString(), Class.forName(entry.getValue().toString()));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletContext().getAttribute("basePath")==null) {
			String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
			request.getServletContext().setAttribute("basePath", basePath+"/");
			request.getServletContext().setAttribute("basePath2", basePath);
		}
		
		ServletObjectUtil.setTHREAD_REQUEST(request);
		ServletObjectUtil.setTHREAD_RESPONSE(response);
		ServletObjectUtil.setTHREAD_PARAMETER(new ParameterUtil(request,"/tmp"));
		String temp[]=RequestUriUtil.splitUri(request);
		String path=null;//定义要跳转的处理路径
		String validateRule=null;//获取验证规则
		boolean flag=true;
		try {//如果出现了错误，则表示验证规则可以不存在，或者本身路径有问题
			ResourceUtil resUtil=new ResourceUtil("cn.mldn.fjn.resource.validation");
			validateRule=resUtil.get(temp[0]+"!"+temp[1]);
		}catch(Exception e) {}
		if(validateRule!=null) {//现在有规则，有规则就需要规则的验证处理
			ParameterValidatorUtil pvu=new ParameterValidatorUtil(validateRule);
			if(!pvu.validate()) {//没有通过验证，需要将所有的错误提示信息发送给客户端
				request.setAttribute("errors",pvu.getErrors());//保存所有的错误提示信息
				//出现了错误之后应该进行一个路径的跳转处理操作，所有的跳转路径可以直接在page.properties中
				path=ActionResourceUtil.getPage(temp[0]+"!"+temp[1]+".errorPage");
				if(path==null) {//可能没有为单独的一个页面配置错误页
					path=ActionResourceUtil.getPage("error.page");//使用公共错误页
				}
				flag=false;
			}
		}
		if(flag) {
			try {//在Servlet里面所关心的话题只有一个：返回的跳转路径
				ActionObjectUtil actionObjectUtil=new ActionObjectUtil(actionMap,temp,request);
				path=actionObjectUtil.handleAcation();
			}catch(Exception e) {
				e.printStackTrace();
			}
			ServletObjectUtil.close();
		}
		if(path!=null) {
			if(!"nopath".equals(path)) {
				request.getRequestDispatcher(path).forward(request, response);
			}
		}else {
			response.getWriter().println("ERROR REQUEST");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(req, resp);
		resp.setCharacterEncoding("UTF-8");
	}
}
