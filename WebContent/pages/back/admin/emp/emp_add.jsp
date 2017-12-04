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
<h1>增加雇员信息、${errors}</h1>
	<form action="<%=url%>" method="post" enctype="multipart/form-data">
		操作用户：<input type="text" name="mid" value="mldnjava"/><br>
		雇员编号：<input type="text" name="empno" value="7369"/><br>
		雇员姓名：<input type="text" name="ename" value="小楠"/><br>
		基本工资：<input type="text" name="sal" value="232.2"/><br>
		雇佣日期：<input type="text" name="hiredate" value="1998-02-26"/><br>
		雇员年龄：<input type="text" name="age" value="20"/><br>
		雇员兴趣：<input type="checkbox" name="inst" value="唱歌" checked="checked">唱歌
				<input type="checkbox" name="inst" value="跳舞">跳舞
				<input type="checkbox" name="inst" value="看书">看书<br>
		雇员权限：<input type="checkbox" name="actid" value="1" checked="checked">增加部门
				<input type="checkbox" name="actid" value="2">部门列表
				<input type="checkbox" name="actid" value="3">公告管理<br>
		图片上传：<input type="file" name="photo"><br>
		<input type="submit" value="提交"/>
		<input type="reset" value="重置"/>
	</form>
</body>
</html>