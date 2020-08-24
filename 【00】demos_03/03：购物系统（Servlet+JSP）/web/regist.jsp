<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Regist</title>
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
        //如果注册失败
        String result = (String) request.getAttribute("result");
        if (result != null) {
            out.write(result + "<br><br>");
        }
    %>

    <form action="register" method="post">
        账号: <input type="text" name="uname" value=""><br>
        密码: <input type="password" name="upassword" value=""><br>
        余额: <input type="text" name="ubalance" value=""><br>
        <br>
        <input type="submit" value="注册">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input id="back" type="button" value="返回">
    </form>

</body>
</html>
