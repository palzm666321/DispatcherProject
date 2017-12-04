<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="jquery/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div>
		<%--   <jsp:include page="/pages/plugins/split_page_search_plugin.jsp"/>   --%>
		<table border="1" width="100%">
			<tr>
				<td>雇员编号</td>
				<td>雇员姓名</td>
			</tr>
			<c:forEach items="${allEmps}" var="emp">
				<tr>
					<td>${emp.empno}</td>
					<td>${emp.ename}</td>
				</tr>
			</c:forEach>
		</table>
		 <jsp:include page="/pages/plugins/split_page_bar_plugin.jsp" /> 
	</div>
</body>
</html>