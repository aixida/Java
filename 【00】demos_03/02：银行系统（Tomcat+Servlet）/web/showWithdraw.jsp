<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showWithdraw</title>
</head>
<body>

    <%
        String aname = request.getParameter("aname");
    %>

    <form action="withdraw" method="post">
        请输入存款金额:<input type="text" name="withdrawBalance" value=""><br>
        <input type="submit" value="确定">
        <input type="hidden" name="aname" value="<%=aname%>">
    </form>

</body>
</html>
