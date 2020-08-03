<%--
  Created by IntelliJ IDEA.
  User: zgh
  Date: 2020/8/3
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showGoodbye</title>
</head>
<body>

    <%
        String aname = request.getParameter("aname");
    %>

    <%=aname%> 愿你我日后有缘再会.<br>
    <a href="index.html">首页</a>

</body>
</html>
