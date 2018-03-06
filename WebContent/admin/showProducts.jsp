<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function deletePro(pid) {
		var con = confirm("确定要删除我吗？");
		if (con) {
			location.href = "${pageContext.request.contextPath }/adminDeleteProduct?pid="
					+ pid;
		}
	}
	function addProduct() {
		location.href = "${pageContext.request.contextPath }/adminAddProduct";
	}
	window.onload = function() {
		var cid = "${condition.cid}"
		var sc = document.getElementById("sc");
		var options = sc.getElementsByTagName("option");
		for (var i = 0; i < options.length; i++) {
			if (cid == options[i].value) {
				options[i].selected = true;
			}
		}
		var is_hot = "${condition.is_hot}"
		var si = document.getElementById("si");
		var options1 = si.getElementsByTagName("option");
		for (var i = 0; i < options1.length; i++) {
			if (is_hot == options1[i].value) {
				options1[i].selected = true;
			}
		}
	}
	
	function searchfn(obj){
		$.post(
			"${pageContext.request.contextPath}/searchServlet",
			{"word":obj.value},
			function(data){
				var content="";
				if(data!=null){
					//服务器返回的数据是一个json数组，因此可以使用数组的length属性
					for(var i=0;i<data.length;i++){
						content+=
							"<div onmouseover='overfn(this)' onclick='clickfn(this)' onmouseout='outfn(this)'>"
							+data[i].pname+"</div>";
					}
					//设置div的开始和结束标签之间的html内容
					$("#searchContentDiv").html(content);
					$("#searchContentDiv").css("display","block");
				}
				
			},
			"json"
		);
	}
	function clickfn(obj){
		//获取obj这个div内的内容
		//$(obj).html()
		//val:是设置表单标签input的value属性的值
		$("#searchInput").val($(obj).html());
		
		
	}
	function overfn(obj){
		$(obj).css("background-color","#999");
	}
	function outfn(obj){
		$(obj).css("background-color","#fff");
	}


</script>
</head>
<body>
	<table width="100%" cellpadding="0px" cellspacing="0px" border="1px">
		<caption>商品列表</caption>
		<tr>
			<td align="left" colspan="7" width="100%">
				<form  action="${pageContext.request.contextPath }/adminSearchProductList" method="post">
				<table>
				<tr>
				<td>				
				<div style="float:left">																	
						<div>
							 商品名称<input id="searchInput" type="text" name="pname" value="${condition.pname }"  size="100px" onkeyup="searchfn(this)" />
						</div>
						<div style="position:relative;width:275px">
							<div id="searchContentDiv" style="position:absolute;
							border:1px solid #999999;width:231px;display:none">	
							</div>
						</div>	
				</div>	
						&nbsp; &nbsp; &nbsp;
				<div style="float: left">
			  			是否热门<select name="is_hot" id="si">
								<option value="">--请选择--</option>
								<option value="1">是</option>
								<option value="0">否</option>
								</select>	
								&nbsp; &nbsp; &nbsp;
						 所属分类 <select name="cid" id="sc">
								<option value="">--请选择--</option>
									<c:forEach items="${categoryList}" var="cat">
								<option value="${cat.cid }">${cat.cname }</option>
									</c:forEach>
								</select>&nbsp; &nbsp; &nbsp; 
							<input type="submit" value="搜索" /> 
							<a href="${pageContext.request.contextPath }/adminProductList">清除</a>		
				</div>
				</td>
				</tr>	
				</table>									
				</form>				
			</td>
		</tr>
		<tr align="right">
			<td colspan="7" width="100%">
				<button onclick="addProduct()">添加商品</button>
			</td>
		</tr>
		<tr>
			<td align="center">序号</td>
			<td align="center">图片</td>
			<td align="center">名称</td>
			<td align="center">门店价格</td>
			<td align="center">是否热门</td>
			<td align="center">编辑</td>
			<td align="center">删除</td>
		</tr>
		<c:forEach items="${productList}" var="pro" varStatus="vs">
			<tr>
				<td align="center">${vs.count}</td>
				<td align="center"><img
					src="${pageContext.request.contextPath }/images/${pro.pimage }"
					width="40px" height="45px" /></td>
				<td align="center">${pro.pname}</td>
				<td align="center">${pro.shop_price}</td>
				<td align="center">${pro.is_hot==1?"是":"否"}</td>
				<td align="center"><a
					href="${pageContext.request.contextPath}/adminEditProduct?pid=${pro.pid }">编辑</a>
				</td>
				<td align="center"><a href="javascript:void(0)"
					onclick="deletePro('${pro.pid}')">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>