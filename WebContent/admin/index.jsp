<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电器商城后台管理系统</title>
</head>
	<frameset rows="15%,*,10%">
		<frame src="${pageContext.request.contextPath}/admin/top.jsp" />
		<frameset cols="15%,*">
			<frame src="${pageContext.request.contextPath}/admin/left.jsp" />
			<frame src="${pageContext.request.contextPath }/admin/right.jsp" name="right" />
		<frame src="${pageContext.request.contextPath}/admin/bottom.jsp" />
		</frameset >		
	</frameset>
</html>