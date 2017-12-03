<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setCharacterEncoding("UTF-8");
	String url=basePath+"pages/back/admin/emp/EmpAction!add.action";
%>
<base href="<%=basePath%>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=url%>" method="post">
		操作用户：<input type="text" name="mid" value="mldnjava"/><br>
		雇员编号：<input type="text" name="empno" value="7369"/><br>
		雇员姓名：<input type="text" name="ename" value="小楠"/><br>
		基本工资：<input type="text" name="sal" value="232.2"/><br>
		雇佣日期：<input type="text" name="hiredate" value="1998-02-26 12:23:53"/><br>
		雇员年龄：<input type="text" name="age" value="20"/><br>
		<input type="submit" value="提交"/>
		<input type="reset" value="重置"/>
	</form>
</body>
</html>