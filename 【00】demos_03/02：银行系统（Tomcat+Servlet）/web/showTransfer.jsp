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
    <title>showTransfer</title>
</head>
<body>

    <%
        String aname = request.getParameter("aname");
    %>

    <form action="transfer" method="post">
        请输入存款金额:<input type="text" name="transferBalance" value=""><br>
        请输入转账用户名:<input type="text" name="transferName" value=""><br>
        <input type="submit" value="确定">
        <input type="hidden" name="aname" value="<%=aname%>">
    </form>

</body>
</html>
