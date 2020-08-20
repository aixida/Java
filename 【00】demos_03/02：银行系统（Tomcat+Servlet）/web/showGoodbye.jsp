<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showGoodbye</title>
</head>
<body>

    <%
        String aname = (String) session.getAttribute("aname");
    %>

    <%=aname%> 愿你我日后有缘再会.<br>
    <a href="index.html">首页</a>

    <%
        session.removeAttribute("aname");
    %>

</body>
</html>
