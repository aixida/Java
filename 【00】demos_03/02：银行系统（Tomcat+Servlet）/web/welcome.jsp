<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

    <%
        String aname = (String) session.getAttribute("aname");
    %>

    ****************************<br>
    欢迎 <%=aname%> 进入ATM系统<br>
    ****************************<br>
    请输入操作选项<br>
    <a href="query">查询</a><br>
    <a href="showDeposit.jsp">存款</a><br>
    <a href="showWithdraw.jsp">取款</a><br>
    <a href="showTransfer.jsp">转账</a><br>
    <a href="showDelete.jsp">注销</a>

</body>
</html>
