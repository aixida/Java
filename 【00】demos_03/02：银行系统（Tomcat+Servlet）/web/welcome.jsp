<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

    <%
        //JSP的内置对象
        String aname = request.getParameter("aname");
    %>
    ****************************<br>
    欢迎 <%=aname%> 进入ATM系统<br>
    ****************************<br>
    请输入操作选项<br>
    <a href="query?aname=<%=aname%>">查询</a><br>
    <a href="showDeposit.jsp?aname=<%=aname%>">存款</a><br>
    <a href="showWithdraw.jsp?aname=<%=aname%>">取款</a><br>
    <a href="showTransfer.jsp?aname=<%=aname%>">转账</a><br>
    <a href="showDelete.jsp?aname=<%=aname%>">注销</a>

</body>
</html>
