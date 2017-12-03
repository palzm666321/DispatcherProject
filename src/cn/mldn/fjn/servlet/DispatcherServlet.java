package cn.mldn.fjn.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.fjn.web.RequestUriUtil;

@SuppressWarnings("serial")
@WebServlet("*.action")
@WebFilter("/pages/back/admin/emp/*")
public class DispatcherServlet extends HttpServlet implements Filter{

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp[]=RequestUriUtil.splitUri(request);
		System.out.println(Arrays.toString(temp));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
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
