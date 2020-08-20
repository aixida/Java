<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript">
        window.onload = function(){
            var inputButton = document.getElementById("back");
            inputButton.onclick = function(){
                window.history.back();
            }
        }
    </script>
</head>
<body>
    <%
        String aname = (String) session.getAttribute("aname");
        Float abalance = (Float)request.getAttribute("abalance");
    %>
    尊敬的 <%=aname%> 用户,您的可用余额为: <%=abalance%><br>
    <input id="back" type="button" value="回去">
</body>
</html>
