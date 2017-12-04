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
import cn.mldn.fjn.util.web.ActionObjectUtil;
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
		ServletObjectUtil.setTHREAD_REQUEST(request);
		ServletObjectUtil.setTHREAD_RESPONSE(response);
		String temp[]=RequestUriUtil.splitUri(request);
		String path=null;
		String validateRule=null;//获取验证规则
		try {//如果出现了错误，则表示验证规则可以不存在，或者本身路径有问题
			ResourceUtil resUtil=new ResourceUtil("cn.mldn.resource.validation");
			validateRule=resUtil.get(temp[0]+"!"+temp[1]);
		}catch(Exception e) {}
		if(validateRule!=null) {//现在有规则，有规则就需要规则的验证处理
			
		}
		try {
			ActionObjectUtil actionObjectUtil=new ActionObjectUtil(actionMap,temp,request);
			path=actionObjectUtil.handleAcation();
		}catch(Exception e) {
			e.printStackTrace();
		}
		ServletObjectUtil.close();
		if(path!=null) {
			request.getRequestDispatcher(path).forward(request, response);
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
