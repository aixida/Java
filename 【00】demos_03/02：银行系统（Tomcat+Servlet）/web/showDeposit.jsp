<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showDeposit</title>
</head>
<body>

    <%
        String aname = request.getParameter("aname");
    %>

    <form action="deposit" method="post">
        请输入存款金额:<input type="text" name="depositBalance" value=""><br>
        <input type="submit" value="确定">
        <input type="hidden" name="aname" value="<%=aname%>">
    </form>

</body>
</html>
