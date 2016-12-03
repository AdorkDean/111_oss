<%@ page contentType="text/html; charset=gb2312" language="java"  %>
<%@page import="java.util.*,com.jspsmart.upload.*,java.lang.*" %>
<%@page import="java.io.File" %>
<%@page import="java.lang.NullPointerException" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'down.jsp' starting page</title>
  </head>
  
  <body>
    <%
    	String xlsname=(String)request.getAttribute("xlsname");
    	SmartUpload su=new SmartUpload();
    	su.initialize(pageContext);
    	su.setContentDisposition(null);
    	su.downloadFile(xlsname);
    	out.clear(); 
        out = pageContext.pushBody();  
     %>
  </body>
</html>
