<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
          <!-- 向小林微信公众号调用图灵. --> <br>
          1.微信公众号调用图灵<br>
          2.微信公众号接收图片消息<br>
          3.接收关键字“最新优惠”，返回图文消息<br>
          4.增加创建菜单工具<br>
          5.增加点击菜单的事件推送<br>
          <img alt="" src="${ pageContext.request.contextPath }/img/imgMsg.png">
          
  </body>
</html>
