<%--
  Created by IntelliJ IDEA.
  User: zgh
  Date: 2020/8/3
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showDelete</title>
</head>
<body>

    <%
        String aname = request.getParameter("aname");
    %>

    <form action="delete" method="post">
        你确定要离我而去了吗？<br>
        <input type="submit" value="确定">
        <input type="hidden" name="aname" value="<%=aname%>">
    </form>

</body>
</html>
